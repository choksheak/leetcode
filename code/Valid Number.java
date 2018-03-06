class Solution {
    public boolean isNumber(String s) {
        if (s == null) {
            return false;
        }
        
        s = s.trim();
        if (s.length() == 0) {
            return false;
        }
        
        // Split string by 'e' symbol if found.
        int expIndex = s.indexOf('e');
        if (expIndex < 0) {
            expIndex = s.indexOf('E');
        }
        
        if (expIndex >= 0) {
            return isNonExpNumber(s, 0, expIndex, true)
                && isNonExpNumber(s, expIndex + 1, s.length(), false);
        }
        
        return isNonExpNumber(s, 0, s.length(), true);
    }
    
    private static boolean isNonExpNumber(String s, int from, int to, boolean allowDot) {
        if (from == to) {
            return false;
        }
        
        int i = from;
        int c = s.charAt(from);
        
        if (c == '-' || c == '+') {
            i++;
        }

        boolean hasDigit = false;

        for (boolean seenDot = false; i < to; i++) {
            c = s.charAt(i);
            if (c == '.') {
                if (!allowDot || seenDot) {
                    return false;
                }
                seenDot = true;
            }
            else if (c >= '0' && c <= '9') {
                hasDigit = true;
            }
            else {
                return false;
            }
        }
        
        return hasDigit;
    }
}
