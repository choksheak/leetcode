class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {                
                if (exists(board, i, j, m, n, word, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean exists(char[][] board, int i, int j, int m, int n, String word, int index) {
        if (board[i][j] != word.charAt(index)) {
            return false;
        }

        if (word.length() == ++index) {
            return true;
        }
        
        char c = board[i][j];
        board[i][j] = '*';
        
        boolean answer = (i > 0 && exists(board, i - 1, j, m, n, word, index))
            || (i < m - 1 && exists(board, i + 1, j, m, n, word, index))
            || (j > 0 && exists(board, i, j - 1, m, n, word, index))
            || (j < n - 1 && exists(board, i, j + 1, m, n, word, index));
        
        board[i][j] = c;
        return answer;
    }
}
