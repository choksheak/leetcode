class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        
        int orig = x;
        int y = 0;
        
        while (x > 0) {
            int i = x / 10;
            y = (y * 10) + (x - (i * 10));
            x = i;
        }
        
        return orig == y;
    }
}
