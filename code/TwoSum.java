class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        Integer j = -1;
        int i;
        
        for (i = 0; i < nums.length; i++) {
            j = map.get(target - nums[i]);
            if (j != null) {
                break;
            }
            map.put(nums[i], i);
        }
        
        return new int[] { j, i };
    }
}
