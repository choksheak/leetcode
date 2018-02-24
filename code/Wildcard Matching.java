class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }
    
    // s: string to check for a match
    // p: the pattern to match
    // si: start index of s
    // pi: start index of p
    private static boolean isMatch(String s, String p, int si, int pi) {
        // If p is exhausted, then s must also be exhausted to match.
        if (pi >= p.length()) {
            return si >= s.length();
        }

        // If no more chars left in s, check that p has only '*' left.
        if (si >= s.length()) {
            for (int i = pi; i < p.length(); i++) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            
            return true;
        }
        
        int pc = p.charAt(pi);
        
        // Match for non-asterisk ('*') which is the simple case.
        if (pc != '*') {
            return (si < s.length())
                && (pc == '?' || s.charAt(si) == pc)
                && isMatch(s, p, si + 1, pi + 1);
        }
        
        // pc == '*', so aggregate consecutive '*'.
        while (++pi < p.length() && p.charAt(pi) == '*');
        
        // Match '*' for each remaining substring of s (including empty substring)
        // with p skipping the current group of consecutive asterisks.
        for (int i = s.length(); i >= si; i--) {
            // Any first match will do.
            if (isMatch(s, p, i, pi)) {
                return true;
            }
        }

        // Didn't find any substring matches for s and pi + 1.
        return false;
    }
}
