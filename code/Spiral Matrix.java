class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<Integer>();
        }
        
        int xlen = matrix[0].length;
        int ylen = matrix.length;
        
        int x1 = 0;
        int x2 = xlen - 1;
        int y1 = 0;
        int y2 = ylen - 1;
        
        int xd = 1;
        int yd = 0;
        
        int x = 0;
        int y = 0;
        int count = xlen * ylen;
        
        List<Integer> list = new ArrayList<>(count);
        
        while (--count >= 0) {
            list.add(matrix[y][x]);
            
            if (xd == 1 && x >= x2) {
                // From right to down.
                xd = 0;
                yd = 1;
                y1++;
            }
            else if (yd == 1 && y >= y2) {
                // From down to left.
                xd = -1;
                yd = 0;
                x2--;
            }
            else if (xd == -1 && x <= x1) {
                // From left to up.
                xd = 0;
                yd = -1;
                y2--;
            }
            else if (yd == -1 && y <= y1) {
                // From up to right.
                xd = 1;
                yd = 0;
                x1++;
            }
            
            x += xd;
            y += yd;            
        }
        
        return list;
    }
}
