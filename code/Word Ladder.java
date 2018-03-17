class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Impossible case here, but zero transformation yields 1, not 0.
        if (beginWord.equals(endWord)) {
            return 1;
        }
        
        // Convert input list to set for quick lookup.
        Set<String> remainingWords = new HashSet<>(wordList);
        
        // No need to keep the begin and end words (if they appear in wordList).
        remainingWords.remove(beginWord);
        
        // Queer case: endWord is itself not a valid word.
        if (!remainingWords.remove(endWord)) {
            return 0;
        }
        
        // BFS queue.
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(beginWord);
        
        // Ladder length starts with 2 for one transformation (including beginWord itself).
        int answer = 2;
        
        do {
            int size = queue.size();
            
            // Traverse by BFS level using current queue size.
            for (int i = 0; i < size; i++) {
                char[] word = queue.removeFirst().toCharArray();
                
                for (int j = 0; j < word.length; j++) {
                    char oldChar = word[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != oldChar) {
                            word[j] = c;
                            String newWord = new String(word);
                            
                            // Got the answer, so return.
                            if (newWord.equals(endWord)) {
                                return answer;
                            }
                            
                            // Only add newWord if we haven't seen it yet.
                            if (remainingWords.remove(newWord)) {
                                queue.addLast(newWord);
                            }
                        }
                    }
                    
                    word[j] = oldChar;
                }
            }
            
            answer++;
        }
        while (!queue.isEmpty());
        
        // 0 means that we did not find a solution.
        return 0;
    }
}
