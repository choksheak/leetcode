class Solution {
    private static final String[] D = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<String>(0);
        }
            
        return letterCombinations(digits, 0);
    }
    
    private static List<String> letterCombinations(String digits, int start) {
        String s = D[digits.charAt(start) - '0'];
        List<String> list = new ArrayList<>();
        
        if (start == digits.length() - 1) {            
            for (int i = 0; i < s.length(); i++) {
                list.add(String.valueOf((char)s.charAt(i)));
            }
        }
        else {
            List<String> list2 = letterCombinations(digits, start + 1);

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                for (String t: list2) {
                    list.add(c + t);
                }
            }
        }
        
        return list;
    }
}
