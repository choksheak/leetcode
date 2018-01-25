public class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        
        if (s.length() == 0) {
            return s;
        }
        
        StringBuilder sb = new StringBuilder(s.length());
        
        for (int i = s.length() - 1; i >= 0; i--) {
            while (s.charAt(i) == ' ') {
                i--;
            }
            
            int j = i;
            while (i > 0 && s.charAt(i - 1) != ' ') {
                i--;
            }
            
            if (sb.length() > 0) {
                sb.append(' ');
            }
            
            for (int k = i; k <= j; k++) {
                sb.append(s.charAt(k));
            }
        }
        
        return sb.toString();
    }
}
