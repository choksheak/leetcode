class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        
        List<List<Integer>> answer = new ArrayList<>();
        combinationSum(answer, new ArrayList<Integer>(), candidates, target, 0);
        return answer;
    }
    
    private static void combinationSum(List<List<Integer>> answer, List<Integer> list, int[] candidates, int target, int start) {
        if (target == 0) {
            answer.add(new ArrayList<Integer>(list));
            return;
        }
        
        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            list.add(candidates[i]);
            combinationSum(answer, list, candidates, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
}
