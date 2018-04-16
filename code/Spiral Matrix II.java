class Solution {
    public int[][] generateMatrix(int n) {
        int[][] m = new int[n][n];
        
        int x = 0;
        int y = 0;
        
        int xd = 1;
        int yd = 0;
        
        int x1 = 0;
        int y1 = 0;
        int x2 = n - 1;
        int y2 = n - 1;
        
        for (int i = 1, end = n * n; i <= end; i++) {
            m[y][x] = i;
            
            if (xd == 1 && x == x2) {
                // From right to down.
                xd = 0;
                yd = 1;
                y1++;
            }
            else if (yd == 1 && y == y2) {
                // From down to left.
                xd = -1;
                yd = 0;
                x2--;
            }
            else if (xd == -1 && x == x1) {
                // From left to up.
                xd = 0;
                yd = -1;
                y2--;
            }
            else if (yd == -1 && y == y1) {
                // From up to right.
                xd = 1;
                yd = 0;
                x1++;
            }

            x += xd;
            y += yd;            
        }
        
        return m;
    }
}
