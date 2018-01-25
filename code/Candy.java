class Solution {
    public int candy(int[] ratings) {
        int[] a = new int[ratings.length];
        
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i] < ratings[i + 1]) {
                a[i + 1] = a[i] + 1;
            }
        }
        
        for (int i = ratings.length - 1; i > 0; i--) {
            if (ratings[i] < ratings[i - 1]) {
                a[i - 1] = Math.max(a[i - 1], a[i] + 1);
            }
        }
        
        int sum = ratings.length;
        for (int i = 0; i < ratings.length; i++) {
            sum += a[i];
        }
        
        return sum;
    }
}
