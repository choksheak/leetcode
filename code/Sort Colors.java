class Solution {
    public void sortColors(int[] nums) {
        int a = 0;
        int b = nums.length - 1;
        
        for (int i = 0; i <= b; i++) {
            if (nums[i] == 0 && i != a) {
                nums[i--] = nums[a];
                nums[a++] = 0;
            }
            else if (nums[i] == 2 && i != b) {
                nums[i--] = nums[b];
                nums[b--] = 2;
            }
        }
    }
}
