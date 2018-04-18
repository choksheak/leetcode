class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        subsets(answer, new ArrayList<Integer>(), nums, 0);
        return answer;
    }
    
    private void subsets(List<List<Integer>> answer, List<Integer> list, int[] nums, int start) {
        answer.add(new ArrayList<Integer>(list));
        
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            subsets(answer, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
