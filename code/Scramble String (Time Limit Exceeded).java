// This solution looks the same like the ones others submitted but got time limit exceeded.
class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            throw new IllegalArgumentException();
        }
        
        return isScramble(s1, 0, s1.length(), s2, 0, s2.length());
    }
    
    private static boolean isScramble(String s1, int start1, int end1, String s2, int start2, int end2) {
        if (end1 - start1 == 1) {
            return s1.charAt(start1) == s2.charAt(start2);
        }
        
        for (int i = 1; start1 + i < end1; i++) {
            // Compare first part to first part.
            if (isScramble(s1, start1 + i, end1, s2, start2 + i, end2)
                && isScramble(s1, start1, start1 + i, s2, start2, start2 + i)) {
                return true;
            }

            // Compare first part to second part.
            if (isScramble(s1, start1, start1 + i, s2, end2 - i, end2)
                && isScramble(s1, start1 + i, end1, s2, start2, end2 - i)) {
                return true;
            }
        }
        
        return false;
    }
}
