class Solution {
    public double myPow(double x, int n) {
        if (x == 0 || Double.isNaN(x) || Double.isInfinite(x)) {
            return 0;
        }
        
        if (n == 0) {
            return 1;
        }
        
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        
        if (n == 1) {
            return x;
        }
        
        return (((n % 2) == 0) ? 1 : x) * myPow(x * x, n / 2);
    }
}
