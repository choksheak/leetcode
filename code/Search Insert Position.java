class Solution {
    public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            if (nums[mid] < target) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        
        hi = Math.max(hi, 0);
        lo = Math.min(lo, nums.length - 1);
        
        for (int i = hi; i <= lo; i++) {
            if (nums[i] > target) {
                return i;
            }
        }
        
        return nums.length;
    }
}
