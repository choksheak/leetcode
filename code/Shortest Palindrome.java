class Solution {
    public String shortestPalindrome(String s) {
        // Find the longest substring from the beginning that is a palindrome.
        int i;
        for (i = s.length() - 1; i > 0; i--) {
            if (isPalindrome(s, 0, i)) {
                break;
            }
        }
        
        // Copy the non-palindrome part to the front.
        return new StringBuilder(s.substring(i + 1)).reverse().append(s).toString();
    }
    
    private static boolean isPalindrome(String s, int from, int to) {
        while (from < to) {
            if (s.charAt(from++) != s.charAt(to--)) {
                return false;
            }
        }
        return true;
    }
}
