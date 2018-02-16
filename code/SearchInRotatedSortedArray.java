class Solution {
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }
    
    private static int search(int[] nums, int target, int lo, int hi) {
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // If this segment is already ordered correctly, then
            // do normal binary search.
            if (nums[lo] < nums[hi]) {
                if (nums[mid] < target) {
                    lo = mid + 1;
                }
                else {
                    hi = mid - 1;
                }
                continue;
            }
            
            // This segment is not ordered, so search in both halves.
            int i = search(nums, target, lo, mid - 1);
            if (i != -1) return i;
                
            return search(nums, target, mid + 1, hi);
        }
        
        return -1;
    }
}
