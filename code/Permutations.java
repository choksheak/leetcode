class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        permute(answer, nums, 0);
        return answer;
    }
    
    private static void permute(List<List<Integer>> answer, int[] nums, int start) {
        if (start == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int n: nums) {
                list.add(n);
            }
            answer.add(list);
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            permute(answer, nums, start + 1);
            swap(nums, start, i);
        }        
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
