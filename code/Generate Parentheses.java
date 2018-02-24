class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();
        generateParenthesis(answer, new char[n * 2], 0, 0, n);
        return answer;
    }
    
    private static void generateParenthesis(
        List<String> answer,
        char[] s,
        int numOpen,
        int numClose,
        int n) {
        
        if (numOpen == n && numClose == n) {
            answer.add(new String(s));
            return;
        }

        int i = numOpen + numClose;

        if (numOpen > numClose) {
            s[i] = ')';
            generateParenthesis(answer, s, numOpen, numClose + 1, n);
        }
        
        if (numOpen < n) {
            s[i] = '(';
            generateParenthesis(answer, s, numOpen + 1, numClose, n);
        }
    }
}
