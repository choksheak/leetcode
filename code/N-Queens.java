class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][];
        for (int i = 0; i < n; i++) {
            board[i] = new char[n];
            Arrays.fill(board[i], '.');
        }
        
        List<List<String>> answer = new ArrayList<>();
        solve(answer, board, 0, n);
        return answer;
    }
    
    private static void solve(List<List<String>> answer, char[][] board, int row, int n) {
        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col, n)) {
                board[row][col] = 'Q';
                
                if (row == n - 1) {
                    answer.add(makeAnswer(board));
                }
                else {
                    solve(answer, board, row + 1, n);
                }
                
                board[row][col] = '.';
                
                if (row == n - 1) {
                    return;
                }
            }
        }
    }
    
    private static boolean isValid(char[][] board, int row, int col, int n) {
        for (int r = 0; r < row; r++) {
            if (board[r][col] == 'Q') {
                return false;
            }
        }
        
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        for (int r = row - 1, c = col + 1; r >= 0 && c < n; r--, c++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }
        
        return true;
    }
    
    private static List<String> makeAnswer(char[][] board) {
        List<String> list = new ArrayList<>(board.length);
        for (char[] a: board) {
            list.add(new String(a));
        }
        return list;
    }
}
