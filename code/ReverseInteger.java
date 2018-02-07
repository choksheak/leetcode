class Solution {
    public int reverse(int x) {
        int y = 0;
        boolean isNeg = (x < 0);
        if (isNeg) x = -x;
        int last;
        
        while (x > 0) {
            int x2 = x / 10;

            last = y;
            y = (y * 10) + (x - (x2 * 10));
            
            if ((y / 10) != last) {
                return 0;
            }
            
            x = x2;
        }
        
        return isNeg ? -y : y;
    }
}
