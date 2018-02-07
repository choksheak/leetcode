class Solution {
    public int myAtoi(String s) {
        int i = 0;
        
        // Skip spaces.
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        
        if (i >= s.length()) {
            return 0;
        }
        
        // Read sign.
        boolean isNeg = false;
        int c = s.charAt(i);
        
        if (c == '+' || c == '-') {
            i++;
            isNeg = (c == '-');
        }
        
        // Read digits.
        long n = 0;
        
        for (; i < s.length(); i++) {
            c = s.charAt(i);
            
            if (c < '0' || c > '9') {
                break;
            }
            
            if (isNeg) {
                n = (n * 10) - (c - '0');
                if (n < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
            else {
                n = (n * 10) + (c - '0');
                if (n > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;                    
                }
            }            
        }
        
        return (int) n;
    }
}
