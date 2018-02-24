class Solution {
    public int calPoints(String[] ops) {
        int[] a = new int[ops.length];
        int n = 0;
        
        for (String op: ops) {
            if (op.equals("C")) {
                n--;
            }
            else if (op.equals("D")) {
                a[n] = a[n - 1] * 2;
                n++;
            }
            else if (op.equals("+")) {
                a[n] = a[n - 2] + a[n - 1];
                n++;
            }
            else {
                a[n] = Integer.parseInt(op);
                n++;
            }
        }
        
        int sum = 0;
        
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        
        return sum;
    }
}
