class Solution {
    // Save boilerplate code on argument passing by making them instance variables.
    private List<List<String>> answer = new ArrayList<>();
    private Map<String, Set<String>> newToOldWords = new HashMap<>();
    private LinkedList<String> tempList = new LinkedList<>();
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // Trivial case.
        if (beginWord.equals(endWord)) {
            answer.add(Arrays.asList(beginWord));
            return answer;
        }
        
        // BFS queue.
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(beginWord);
        
        // Track the minimum number of steps needed to reach each word.
        Map<String, Integer> minStepsToWord = new HashMap<>();
        for (String word: wordList) {
            minStepsToWord.put(word, Integer.MAX_VALUE);
        }
        
        int minStepsToEndWord = Integer.MAX_VALUE;
        int numSteps = 2;
        
        do {
            // Traverse by each search level.
            for (int level = queue.size(); level > 0; level--) {
                String oldWord = queue.removeFirst();
                char[] word = oldWord.toCharArray();

                for (int i = 0; i < word.length; i++) {
                    char oldChar = word[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == oldChar) {
                            continue;
                        }
                        
                        word[i] = c;
                        String newWord = new String(word);

                        // If this word is not valid, continue.
                        if (!minStepsToWord.containsKey(newWord)) {
                            continue;
                        }

                        int minStepsToNewWord = minStepsToWord.get(newWord);

                        // If this is not the shortest path to newWord, exclude it.
                        if (minStepsToNewWord < numSteps) {
                            continue;
                        }

                        // Set the minimum number of steps needed to get to newWord.
                        if (minStepsToNewWord > numSteps) {
                            minStepsToWord.put(newWord, numSteps);
                        }

                        // Map new word to old word.
                        Set<String> set = newToOldWords.get(newWord);
                        if (set == null) {
                            set = new HashSet<String>();
                            newToOldWords.put(newWord, set);
                        }
                        set.add(oldWord);

                        if (newWord.equals(endWord)) {
                            // Track minimum number of steps to get to endWord.
                            minStepsToEndWord = Math.min(minStepsToEndWord, numSteps);
                        }
                        else if (minStepsToNewWord > numSteps) {
                            // If word was already processed, do not add it to the queue again.
                            queue.addLast(newWord);
                        }
                    }

                    word[i] = oldChar;
                }
            }
            
            numSteps++;
        }
        while (!queue.isEmpty());
        
        // Collect answer as all possible paths from endWord to beginWord.
        backTraceWords(beginWord, endWord);
        
        return answer;
    }
    
    private void backTraceWords(String beginWord, String endWord) {
        tempList.addFirst(endWord);
        
        if (beginWord.equals(endWord)) {
            answer.add(new ArrayList<String>(tempList));
        }
        else {
            Set<String> oldWords = newToOldWords.get(endWord);
            if (oldWords != null) {
                for (String oldWord: oldWords) {
                    backTraceWords(beginWord, oldWord);
                }
            }
        }

        tempList.removeFirst();
    }
}
