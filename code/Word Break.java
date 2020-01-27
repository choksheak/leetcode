class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty()) {
            return true;
        }
        if (wordDict.isEmpty()) {
            return false;
        }
        
        Set<String> dictSet = new HashSet<>(wordDict);
        int shortestWordLength = wordDict.get(0).length();
        int longestWordLength = 0;
        
        for (String word: wordDict) {
            shortestWordLength = Math.min(shortestWordLength, word.length());
            longestWordLength = Math.max(longestWordLength, word.length());
        }
        
        boolean[] isValid = new boolean[s.length() + 1];
        int startIndex = 0;
        
        while (startIndex < s.length()) {
            int end = Math.min(startIndex + longestWordLength, s.length());
            
            for (int i = startIndex + shortestWordLength; i <= end; i++) {
                if (isValid[i]) {
                    continue;
                }
                String word = s.substring(startIndex, i);
                if (dictSet.contains(word)) {
                    isValid[i] = true;
                }
            }
            
            while (++startIndex < s.length()) {
                if (isValid[startIndex]) {
                    break;
                }
            }
        }
        
        return isValid[s.length()];
    }
}
