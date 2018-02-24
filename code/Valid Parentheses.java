class Solution {
    public boolean isValid(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            
            if (c == ')') {
                if (stack.isEmpty() || stack.removeLast() != '(') {
                    return false;
                }
            }
            else if (c == '}') {
                if (stack.isEmpty() || stack.removeLast() != '{') {
                    return false;
                }
            }
            else if (c == ']') {
                if (stack.isEmpty() || stack.removeLast() != '[') {
                    return false;
                }
            }
            else {
                stack.addLast(c);
            }
        }
        
        return stack.isEmpty();
    }
}
