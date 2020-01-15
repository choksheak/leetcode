class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }
    
    private static List<List<Integer>> kSum(int[] nums, int target, int k, int start) {
        List<List<Integer>> answer = new ArrayList<>();
        
        if (k == 2) {
            int lo = start;
            int hi = nums.length - 1;
            
            while (lo < hi) {
                int a = nums[lo];
                int b = nums[hi];
                int sum = a + b;
                
                if (sum == target) {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(a);
                    newList.add(b);
                    answer.add(newList);
                    
                    while (++lo < hi && nums[lo] == a);
                    while (--hi > lo && nums[hi] == b);
                }
                else if (sum < target) {
                    while (++lo < hi && nums[lo] == a);                    
                }
                else {
                    while (--hi > lo && nums[hi] == b);                    
                }
            }
        }
        else {
            for (int i = start, last = nums.length - k + 1; i < last;) {
                int n = nums[i];
                List<List<Integer>> subAnswer = kSum(nums, target - n, k - 1, i + 1);
                
                for (List<Integer> list: subAnswer) {
                    list.add(n);
                    answer.add(list);
                }
                
                while (++i < last && nums[i] == n);
            }
        }
        
        return answer;
    }
}
