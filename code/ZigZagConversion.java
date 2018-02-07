class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        
        StringBuilder[] a = new StringBuilder[numRows];
        
        for (int i = 0; i < numRows; i++) {
            a[i] = new StringBuilder();
        }
        
        for (int i = 0, row = 0, dir = 1; i < s.length(); i++) {
            a[row].append((char) s.charAt(i));
            
            row += dir;
            
            if (row == numRows) {
                dir = -1;
                row = numRows - 2;
            }
            else if (row == -1) {
                dir = 1;
                row = 1;
            }
        }
        
        for (int i = 1; i < numRows; i++) {
            a[0].append(a[i]);
        }
        
        return a[0].toString();
    }
}
