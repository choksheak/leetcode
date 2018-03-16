class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] seen = new boolean[9];
        
        for (int i = 0; i < 9; i++) {
            Arrays.fill(seen, false);
            
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                
                if (c != '.') {
                    if (seen[c - '1']) {
                        return false;
                    }
                    seen[c - '1'] = true;
                }
            }

            Arrays.fill(seen, false);
            
            for (int j = 0; j < 9; j++) {
                char c = board[j][i];
                
                if (c != '.') {
                    if (seen[c - '1']) {
                        return false;
                    }
                    seen[c - '1'] = true;
                }
            }
        }
        
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Arrays.fill(seen, false);
                
                for (int k = 0; k < 3; k++) {
                    for (int m = 0; m < 3; m++) {
                        char c = board[i + k][j + m];
                        
                        if (c != '.') {
                            if (seen[c - '1']) {
                                return false;
                            }
                            seen[c - '1'] = true;
                        }
                    }
                }
            }
        }
        
        return true;
    }
}
