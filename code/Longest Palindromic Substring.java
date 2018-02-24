class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int[] max = { 0, 1 };
        
        for (int i = 0; i < s.length(); i++) {
            longestPalindrome(s, i, i, max);
            longestPalindrome(s, i, i + 1, max);
        }
        
        return s.substring(max[0], max[1]);
    }
    
    private static void longestPalindrome(String s, int start1, int start2, int[] max) {
        while (start1 >= 0 && start2 < s.length() && s.charAt(start1) == s.charAt(start2)) {
            start1--;
            start2++;
        }
        
        int len = start2 - start1 - 1;
        if (len > max[1] - max[0]) {
            max[0] = start1 + 1;
            max[1] = start2;
        }
    }
}
