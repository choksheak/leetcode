class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> answer = new ArrayList<>();

        for (int ai = 0, lastAi = nums.length - 2, last = nums.length - 1; ai < lastAi;) {
            int a = nums[ai];
            int bi = ai + 1;
            int ci = last;
            
            while (bi < ci) {
                int b = nums[bi];
                int c = nums[ci];
                int sum = a + b + c;
                
                if (sum == 0) {
                    answer.add(Arrays.asList(a, b, c));
                    while (++bi < ci && nums[bi] == b);
                    while (--ci > bi && nums[ci] == c);
                }
                else if (sum < 0) {
                    while (++bi < ci && nums[bi] == b);
                }
                else {
                    while (--ci > bi && nums[ci] == c);
                }
            }
            
            while (++ai < lastAi && nums[ai] == a);
        }
        
        return answer;
    }
}
