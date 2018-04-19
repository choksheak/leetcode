class Solution {
    public boolean search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }
    
    private static boolean search(int[] nums, int target, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            if (nums[lo] <= target && target < nums[mid]) {
                // Target is in left half (if present).
                hi = mid - 1;
            }
            else if (nums[mid] < target && target <= nums[hi]) {
                // Target is in right half (if present).
                lo = mid + 1;
            }
            else {
                // We can't know for sure which half the target is in,
                // so we need to search both halves.
                return search(nums, target, lo, mid - 1)
                    || search(nums, target, mid + 1, hi);
            }
        }

        return false;
    }
}
