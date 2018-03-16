class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int numRows = dungeon.length;
        int numCols = dungeon[0].length;
            
        int[][] a = new int[numRows + 1][numCols + 1];
        
        for (int row = numRows - 1; row >= 0; row--) {
            for (int col = numCols - 1; col >= 0; col--) {
                if (row == numRows - 1 && col == numCols - 1) {
                    // This is the ending room.
                    a[row][col] = Math.max(1, 1 - dungeon[row][col]);
                }
                else if (row == numRows - 1) {
                    // At the last row, we can only go right.
                    a[row][col] = Math.max(1, a[row][col + 1] - dungeon[row][col]);
                }
                else if (col == numCols - 1) {
                    // At the last column, we can only go down.
                    a[row][col] = Math.max(1, a[row + 1][col] - dungeon[row][col]);
                }
                else {
                    // At any other room, we can go either right or down.
                    int min = Math.min(a[row + 1][col], a[row][col + 1]);
                    a[row][col] = Math.max(1, min - dungeon[row][col]);
                }
            }
        }
        
        // Return minimum starting health, not the ending health.
        return a[0][0];
    }
}
