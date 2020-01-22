import java.util.*;

/*
Given list of words sorted, in alien alphabet. Return the alphabets in sorted order.

Topological sort algorithm
*/
public class Main {

    public static void main(String[] args) {
        List<String> input = Arrays.asList(
                "ccda",
                "ccbk",
                "cd",
                "a",
                "b"
        );

        System.out.println("Code 1 = " + AlienAlphabetsReader1.getAlphabetsInOrder(input));  // [k, c, a, d, b]
        System.out.println("Code 2 = " + AlienAlphabetsReader2.getAlphabetsInOrder(input));  // [k, c, a, d, b]
    }
}

// Actual code (with minor cleanups) written up during the interview - Jan 2020.
class AlienAlphabetsReader1 {

    // This graph representation is not ideal, but works fine.
    private static class Graph {
        private Map<Character, Set<Character>> before = new HashMap<>(); // c -> set of chars that come before c
        private Map<Character, Set<Character>> after = new HashMap<>(); // c -> set of chars that come after c
    }

    static List<Character> getAlphabetsInOrder(List<String> words) {
        if (words.size() == 0) {
            return Collections.emptyList();
        }

        List<Character> answer = new ArrayList<>();

        // Build graph.
        Graph graph = buildGraph(words);

        // Collect all the characters that appeared.
        Set<Character> allChars = collectAllCharacters(words);

        // Whichever nodes have no edges, put as first alphabets.
        for (char c : allChars) {
            if (!graph.before.containsKey(c) && !graph.after.containsKey(c)) {
                answer.add(c);
            }
        }

        // Add all nodes that does not appear in before, but in after.
        for (char a : graph.after.keySet()) {
            if (!graph.before.containsKey(a)) {
                answer.add(a);

                for (char c : graph.before.keySet()) {
                    graph.before.get(c).remove(a);
                }
            }
        }

        // Whichever nodes have no come before relationship. extract it first.
        Set<Character> toRemove = new HashSet<>();

        while (!graph.before.isEmpty()) {
            toRemove.clear();

            for (char c : graph.before.keySet()) {
                if (graph.before.get(c).isEmpty()) {
                    answer.add(c);

                    // Avoid concurrent modification exception by removing it outside the loop.
                    toRemove.add(c);
                }
            }

            for (char c : toRemove) {
                graph.before.remove(c);

                // Remove elements, but not the keys as we will need them above.
                for (char c2 : graph.before.keySet()) {
                    graph.before.get(c2).remove(c);
                }
            }
        }

        return answer;
    }

    private static Graph buildGraph(List<String> words) {
        Graph graph = new Graph();

        for (int i = 1; i < words.size(); i++) {
            String w1 = words.get(i - 1);
            String w2 = words.get(i);

            int len = Math.min(w1.length(), w2.length());
            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if (c1 != c2) {
                    // Add an edge.
                    graph.before.computeIfAbsent(c2, k -> new HashSet<>()).add(c1);
                    graph.after.computeIfAbsent(c1, k -> new HashSet<>()).add(c2);
                    break;
                }
            }
        }

        return graph;
    }

    private static Set<Character> collectAllCharacters(List<String> words) {
        Set<Character> set = new HashSet<>();

        for (String w : words) {
            for (char c : w.toCharArray()) {
                set.add(c);
            }
        }

        return set;
    }
}

// Attempt to rewrite the above logic using optimal code.
class AlienAlphabetsReader2 {

    // This graph representation is quite optimal for this question.
    private static class Graph {
        private Map<Character, Set<Character>> nextNodes = new HashMap<>();
    }

    static List<Character> getAlphabetsInOrder(List<String> words) {
        if (words.size() == 0) {
            return Collections.emptyList();
        }

        List<Character> answer = new ArrayList<>();

        // Build graph.
        Graph graph = buildGraph(words);

        // Collect all the characters that appeared.
        Set<Character> allChars = collectAllCharacters(words);

        // Calculate number of in-edges for each node.
        Map<Character, Integer> inEdgesCount = getInEdgesCounts(graph);

        // Whichever nodes have no edges, put as first alphabets.
        for (char c : allChars) {
            if (!inEdgesCount.containsKey(c)) {
                answer.add(c);
            }
        }

        // Loop to remove all nodes with in-edges counts of zero first.
        List<Character> toRemove = new ArrayList<>();

        while (!inEdgesCount.isEmpty()) {
            toRemove.clear();

            for (Map.Entry<Character, Integer> entry : inEdgesCount.entrySet()) {
                if (entry.getValue() == 0) {
                    // Do removal outside of loop to avoid concurrent modification exception.
                    toRemove.add(entry.getKey());
                }
            }

            if (toRemove.isEmpty()) {
                throw new IllegalArgumentException("Cycle found in graph -> no possible answer");
            }

            for (char c : toRemove) {
                answer.add(c);
                inEdgesCount.remove(c);

                // Decrement in-edges count for each of the next nodes.
                if (graph.nextNodes.containsKey(c)) { // last node will be absent as it has no next nodes
                    for (char next : graph.nextNodes.get(c)) {
                        if (inEdgesCount.containsKey(next)) {
                            inEdgesCount.put(next, inEdgesCount.get(next) - 1);
                        }
                    }
                }
            }
        }

        return answer;
    }

    private static Graph buildGraph(List<String> words) {
        Graph graph = new Graph();

        for (int i = 1; i < words.size(); i++) {
            String w1 = words.get(i - 1);
            String w2 = words.get(i);

            int len = Math.min(w1.length(), w2.length());
            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if (c1 != c2) {
                    // Add an edge.
                    graph.nextNodes.computeIfAbsent(c1, k -> new HashSet<>()).add(c2);
                    break;
                }
            }
        }

        return graph;
    }

    private static Set<Character> collectAllCharacters(List<String> words) {
        Set<Character> set = new HashSet<>();

        for (String w : words) {
            for (char c : w.toCharArray()) {
                set.add(c);
            }
        }

        return set;
    }

    private static Map<Character, Integer> getInEdgesCounts(Graph graph) {
        Map<Character, Integer> counts = new HashMap<>();

        for (Map.Entry<Character, Set<Character>> entry : graph.nextNodes.entrySet()) {
            // Add empty characters as they should also be counted too.
            counts.putIfAbsent(entry.getKey(), 0);

            for (char c : entry.getValue()) {
                counts.merge(c, 1, Integer::sum);
            }
        }

        return counts;
    }
}
