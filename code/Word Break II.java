class Solution {
    private Map<String, List<String>> cache = new HashMap<>();
    private Set<String> dictSet;
    private int shortestWordLength;
    private int longestWordLength;
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0 || wordDict.size() == 0) {
            return Collections.emptyList();
        }
        
        dictSet = new HashSet<>(wordDict);
        
        shortestWordLength = wordDict.get(0).length();
        longestWordLength = 0;
        
        for (String w : wordDict) {
            shortestWordLength = Math.min(shortestWordLength, w.length());
            longestWordLength = Math.max(longestWordLength, w.length());
        }
        
        return wordBreak(s);
    }
    
    private List<String> wordBreak(String s) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        if (s.length() == 0) {
            return Collections.singletonList("");
        }

        List<String> sentences = new ArrayList<>();
        int end = Math.min(longestWordLength, s.length());
        
        for (int i = shortestWordLength; i <= end; i++) {
            String t = s.substring(0, i);
            if (dictSet.contains(t)) {
                List<String> list = wordBreak(s.substring(i));
                for (String u : list) {
                    sentences.add(t + (u.isEmpty() ? "" : " " + u));
                }
            }
        }
        
        cache.put(s, sentences);
        return cache.get(s);
    }
}
