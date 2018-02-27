class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        permuteUnique(answer, nums, 0, new HashSet<>());
        return answer;
    }
    
    private static void permuteUnique(List<List<Integer>> answer, int[] nums, int start, Set<String> seen) {
        if (start == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int n: nums) {
                list.add(n);
            }
            
            String s = list.toString();
            if (seen.add(s)) {
                answer.add(list);
            }
            
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            swap(nums, i, start);
            permuteUnique(answer, nums, start + 1, seen);
            swap(nums, i, start);
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
