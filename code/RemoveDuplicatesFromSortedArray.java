class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        
        if (len <= 1) {
            return len;
        }
        
        int length = 1;
        int last = nums[0];
        
        for (int i = 1; i < len; i++) {
            int n = nums[i];
            
            if (n != last) {
                nums[length++] = last = n;
            }
        }
        
        return length;
    }
}
