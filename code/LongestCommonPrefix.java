class Solution {
    public String longestCommonPrefix(String[] strs) {
        int strsLen = strs.length;
        
        if (strsLen == 0) {
            return "";
        }
        
        String s = strs[0];
        
        for (int i = 0, sLen = s.length(); i < sLen; i++) {
            int c = s.charAt(i);
            
            for (int j = 1; j < strsLen; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return s.substring(0, i);
                }
            }
        }
        
        return s;
    }
}
