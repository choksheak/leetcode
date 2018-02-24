class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> answer = new ArrayList<>();
        List<String> current = new ArrayList<>();
        
        partition(answer, current, s, 0);
        
        return answer;
    }
    
    private static void partition(
        List<List<String>> answer,
        List<String> current,
        String s,
        int start) {
        
        if (start >= s.length()) {
            answer.add(new ArrayList<String>(current));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                current.add(s.substring(start, i + 1));
                partition(answer, current, s, i + 1);
                current.remove(current.size() - 1);
            }
        }
    }
    
    private static boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
