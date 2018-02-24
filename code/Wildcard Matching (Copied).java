class Solution {
    // s: string to check for a match
    // p: the pattern to match
    // si: start index of s
    // pi: start index of p
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        int si = 0;
        int pi = 0;
        int asterisk = -1;
        int match = -1;
        
        while (si < sLen) {
            if (pi < pLen && p.charAt(pi) == '*') {
                match = si; 
                asterisk = pi++;
            }
            else if (pi < pLen
                    && (p.charAt(pi) == '?' || s.charAt(si) == p.charAt(pi))) {
                si++; 
                pi++;
            }
            else if (asterisk >= 0) {
                si = ++match;
                pi = asterisk + 1;
            }
            else return false;
        }
        
        while (pi < pLen && p.charAt(pi) == '*') {
            pi++;
        }
        
        return pi == pLen;
    }
}
