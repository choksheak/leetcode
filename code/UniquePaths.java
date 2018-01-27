class Solution {
    public int uniquePaths(int m, int n) {
        int[] matrix = new int[m * n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i * n + j] = (i == 0 || j == 0)
                    ? 1
                    : matrix[(i - 1) * n + j] + matrix[i * n + (j - 1)];
            }
        }
        
        return matrix[(m - 1) * n + (n - 1)];
    }
}
