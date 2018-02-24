class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        
        int closest = nums[0] + nums[1] + nums[2];
        int diff = Math.abs(closest - target);
        
        if (diff == 0) {
            return target;
        }
        
        for (int ai = 0; ai < nums.length - 2;) {
            int a = nums[ai];
            int bi = ai + 1;
            int ci = nums.length - 1;
            
            while (bi < ci) {
                int b = nums[bi];
                int c = nums[ci];
                int sum = a + b + c;
                int thisDiff = Math.abs(sum - target);
                
                if (thisDiff < diff) {
                    if (sum == target) {
                        return sum;
                    }
                    closest = sum;
                    diff = thisDiff;
                }
                
                if (sum < target) {
                    while (++bi < ci && nums[bi] == b);
                }
                else {
                    while (--ci > bi && nums[ci] == c);
                }
            }

            while (++ai < nums.length - 2 && nums[ai] == a);
        }
        
        return closest;
    }
}
