// Given an array of integers, find out whether there are two distinct indices i and j
// in the array such that the absolute difference between nums[i] and nums[j] is at most t
// and the absolute difference between i and j is at most k.

// NON-OPTIMAL SOLUTION

class Solution {
    // k - distance, t - difference
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k <= 0) return false;
        if (t < 0) return false;
        
        int n = nums.length;
        for (int i = 0, last = n - 1; i < last; i++) {
            for (int j = 1; j <= k && i + j < n; j++) {
                // To avoid integer overflow, we need to use long.
                long a = nums[i];
                long b = nums[i + j];
                
                if (Math.abs(a - b) <= t) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
