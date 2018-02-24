class Solution {
    public int numSquares(int n) {
        int[] a = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            for (int j = 1;; j++) {
                int k = i + (j * j);
                
                if (k > n) {
                    break;
                }
                
                a[k] = (a[k] == 0)
                    ? a[i] + 1
                    : Math.min(a[i] + 1, a[k]);
            }
        }
        
        return a[n];
    }
}
