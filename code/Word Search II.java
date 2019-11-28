// Given a 2D board and a list of words from the dictionary, find all words in the board.

// Each word must be constructed from letters of sequentially adjacent cell, where "adjacent"
// cells are those horizontally or vertically neighboring. The same letter cell may not be
// used more than once in a word.

// Hacky Optimizations:
// 1. Trie.chars should be stored as a char[26] array.
// 2. Instead of "seen", mark seen on the board instead.

class Solution {
    private static class Trie {
        Map<Character, Trie> chars = new HashMap<>();
        String word;
        
        private Trie() {}
        
        Trie(String[] words) {
            for (String w : words) {
                Trie t1 = this;
                for (int i = 0; i < w.length(); i++) {
                    char c = w.charAt(i);
                    if (t1.chars.containsKey(c)) {
                        t1 = t1.chars.get(c);
                    } else {
                        Trie t2 = new Trie();
                        t1.chars.put(c, t2);
                        t1 = t2;
                    }
                }
                t1.word = w;
            }
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {        
        if (board.length == 0 || board[0].length == 0 || words.length == 0) {
            return new ArrayList<String>();
        }
        
        Set<String> found = new HashSet<>();
        Trie trie = new Trie(words);
        Set<String> seen = new HashSet<>();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, trie, i, j, found, seen);
            }
        }
        
        return new ArrayList<String>(found);
    }
    
    private static void dfs(char[][] board, Trie trie, int i, int j,
                            Set<String> found, Set<String> seen) {
        String key = i + "," + j;
        if (seen.contains(key)) return;
        seen.add(key);

        char ch = board[i][j];
        if (!trie.chars.containsKey(ch)) {
            seen.remove(key);
            return;
        }
        trie = trie.chars.get(ch);
        if (trie.word != null) {
            found.add(trie.word);
        }
        
        // Left.
        if (i > 0) {
            dfs(board, trie, i - 1, j, found, seen);
        }

        // Right.
        if (i < board.length - 1) {
            dfs(board, trie, i + 1, j, found, seen);
        }

        // Up.
        if (j > 0) {
            dfs(board, trie, i, j - 1, found, seen);
        }

        // Down.
        if (j < board[0].length - 1) {
            dfs(board, trie, i, j + 1, found, seen);
        }
        
        seen.remove(key);
    }
}
