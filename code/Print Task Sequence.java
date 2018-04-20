import java.util.*;

public class Solution {

    private static class Node {
        public final int taskNum;
        public final Set<Integer> prereqTasks = new HashSet<>();
        public final Set<Integer> dependentTasks = new HashSet<>();
        
        public Node(int taskNum) {
            this.taskNum = taskNum;
        }
    }
    
    // List<Integer> = [ A, B ] where task B depends on A.
    private static void printTaskSequence(List<List<Integer>> tasks) {
        // Create the graph.
        Map<Integer, Node> graph = new HashMap<>();
        
        for (List<Integer> task: tasks) {
            // Task B depends on A.
            int a = task.get(0);
            int b = task.get(1);
            
            Node nodeA = graph.get(a);
            Node nodeB = graph.get(b);
            
            if (nodeA == null) {
                graph.put(a, nodeA = new Node(a));
            }

            if (nodeB == null) {
                graph.put(b, nodeB = new Node(b));
            }
            
            nodeB.prereqTasks.add(a);
            nodeA.dependentTasks.add(b);
        }
        
        // Start BFS with all nodes that do not have any prereqs.
        LinkedList<Node> queue = new LinkedList<>();
        
        for (Node node: graph.values()) { // O(N)
            if (node.prereqTasks.isEmpty()) {
                queue.addLast(node);
            }
        }
        
        Set<Integer> isPrinted = new HashSet<>();
        
        while (!queue.isEmpty()) { // O(V)
            Node node = queue.removeFirst();
            
            System.out.println("Process " + node.taskNum);
            isPrinted.add(node.taskNum);
            
            for (Integer dependent: node.dependentTasks) { // O(E)
                if (!isPrinted.contains(dependent)) {
                    boolean canSchedule = true;
                    
                    Node dependentNode = graph.get(dependent);
                    
                    for (Integer prereq: dependentNode.prereqTasks) { // O(E)
                        if (!isPrinted.contains(prereq)) {
                            // Prereq not satified yet, so skip.
                            canSchedule = false;
                            break;
                        }
                    }
                    
                    if (canSchedule) {
                        queue.addLast(dependentNode);
                    }
                }
            }
        }
        
        if (isPrinted.size() != graph.size()) {
            System.out.println("Not all nodes have been scheduled.");
        }
    }
    
    public static void main(String[] args) {
        List<List<Integer>> tasks = new ArrayList<>();
        
        tasks.add(Arrays.asList(1, 2));
        tasks.add(Arrays.asList(2, 4));
        tasks.add(Arrays.asList(2, 3));
        tasks.add(Arrays.asList(1, 4));
        
        printTaskSequence(tasks);
    }
}
