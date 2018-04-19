class Solution {
    public int removeDuplicates(int[] nums) {
        int len = Math.min(2, nums.length);
        
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > nums[len - 2]) {
                if (i != len) {
                    nums[len] = nums[i];
                }
                len++;
            }
        }
        
        return len;
    }
}
