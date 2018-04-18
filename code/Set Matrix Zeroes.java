class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean isFirstColZero = false;
        
        // Use first row and column to indicate whether row/column should be zero.
        // For first column, use a boolean.
        for (int i = 0; i < m; i++) {
            if (!isFirstColZero && matrix[i][0] == 0) {
                isFirstColZero = true;
            }
            
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        
        // Trick: must populate from bottom-up.
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            
            if (isFirstColZero) {
                matrix[i][0] = 0;
            }            
        }
    }
}
