class Solution {
    public int lengthOfLastWord(String s) {
        int lastNonSpace = s.length() - 1;
        
        while (lastNonSpace >= 0 && s.charAt(lastNonSpace) == ' ') {
            lastNonSpace--;
        }
        
        if (lastNonSpace < 0) {
            return 0;
        }
        
        for (int i = lastNonSpace - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                return lastNonSpace - i;
            }
        }
        
        return lastNonSpace + 1;
    }
}
