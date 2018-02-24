class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int max = 1;
        
        for (int i = 0, j = 0; j < s.length(); j++) {
            int c = s.charAt(j);
            Integer k = map.get(c);
            
            if (k != null) {
                i = Math.max(i, k + 1);
            }
            
            max = Math.max(max, j - i + 1);
            map.put(c, j);
        }
        
        return max;
    }
}
