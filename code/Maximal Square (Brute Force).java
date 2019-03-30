class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int max = 0;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != '1') {
                    continue;
                }
                max = Math.max(max, 1);
                for (int distance = 1;; distance++) {
                    if (!isAllOnes(matrix, i, j, distance)) {
                        break;
                    }
                    max = Math.max(max, distance + 1);
                }
            }
        }
        
        return max * max;
    }
    
    private static boolean isAllOnes(char[][] matrix, int i, int j, int distance) {
        if (i + distance >= matrix.length || j + distance >= matrix[0].length) {
            return false;
        }
        
        int i2 = i + distance;
        for (int j2 = 0; j2 <= distance; j2++) {
            if (matrix[i2][j + j2] != '1') {
                return false;
            }
        }
        
        int j2 = j + distance;
        for (i2 = 0; i2 <= distance; i2++) {
            if (matrix[i + i2][j2] != '1') {
                return false;
            }
        }
        
        return true;
    }
}