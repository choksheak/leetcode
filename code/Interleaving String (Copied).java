class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if (len1 + len2 != len3) {
            return false;
        }
        
        boolean[][] a = new boolean[len1 + 1][len2 + 1];
        a[0][0] = true;
        
        for (int i = 0; i < len1; i++) {
            a[i + 1][0] = a[i][0] && (s1.charAt(i) == s3.charAt(i));
        }

        for (int i = 0; i < len2; i++) {
            a[0][i + 1] = a[0][i] && (s2.charAt(i) == s3.charAt(i));
        }
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c3 = s3.charAt(i + j - 1);
                
                a[i][j] = (a[i - 1][j] && (s1.charAt(i - 1) == c3))
                    || (a[i][j - 1] && (s2.charAt(j - 1) == c3));
            }
        }
        
        return a[len1][len2];
    }
}
