class Solution {
    private int count;
    
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[n * 2];
        boolean[] diag2 = new boolean[n * 2];
        
        count = 0;
        countQueens(cols, diag1, diag2, n, 0);
        return count;
    }
    
    public void countQueens(boolean[] cols, boolean[] diag1, boolean[] diag2, int n, int row) {
        int d1;
        int d2;
        
        for (int c = 0; c < n; c++) {
            if (cols[c] || diag1[d1 = row + c] || diag2[d2 = row + (n - c)]) {
                continue;
            }
            
            if (row == n - 1) {
                count++;
                return;
            }
            
            cols[c] = diag1[d1] = diag2[d2] = true;
            countQueens(cols, diag1, diag2, n, row + 1);
            cols[c] = diag1[d1] = diag2[d2] = false;
        }
    }
}
