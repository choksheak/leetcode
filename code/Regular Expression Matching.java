class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }
    
    private static boolean isMatch(String s, int si, String p, int pi) {
        while (true) {
            if (pi >= p.length()) {
                return si >= s.length();
            }

            int pc = p.charAt(pi);

            // Star.
            if ((pi < p.length() - 1) && (p.charAt(pi + 1) == '*')) {
                for (pi += 2; si <= s.length(); si++) {
                    if (isMatch(s, si, p, pi)) {
                        return true;
                    }
                    if ((pc != '.') && (si < s.length()) && (s.charAt(si) != pc)) {
                        break;
                    }
                }
                return false;            
            }

            // No more string to match.
            if (si >= s.length()) {
                return false;
            }

            // Match single character.
            if ((pc != '.') && (pc != s.charAt(si))) {
                return false;
            }

            // Optimize away tail recursion.
            si++;
            pi++;
        }
    }
}
