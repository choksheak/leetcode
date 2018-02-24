class Solution {
    public int[] searchRange(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            
            if (nums[mid] == target) {
                int i = mid;
                while (i > 0 && nums[i - 1] == target) i--;
                
                int j = mid;
                while (j < nums.length - 1 && nums[j + 1] == target) j++;
                
                return new int[] { i, j };
            }
            else if (nums[mid] < target) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        
        return new int[] { -1, -1 };
    }
}
