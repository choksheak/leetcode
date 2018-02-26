// Note: Using Dynamic Programming will exceed the time limit.
class Solution {
    public int jump(int[] nums) {
        int numJumps = 0;
        int curFarthest = 0;
        int curFrom = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            // Track the furthest point we can currently reach.
            curFarthest = Math.max(curFarthest, i + nums[i]);
            
            if (i == curFrom) {
                numJumps++;
                curFrom = curFarthest;
            }
        }
        
        return numJumps;
    }
}
