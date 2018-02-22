class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        
        List<List<Integer>> answer = new ArrayList<>();
        combinationSum2(answer, new ArrayList<Integer>(), new HashSet<String>(), candidates, target, 0);
        return answer;
    }
    
    private static void combinationSum2(
        List<List<Integer>> answer,
        List<Integer> list,
        Set<String> seen,
        int[] candidates,
        int target,
        int start) {
        
        if (target == 0) {
            if (seen.add(list.toString())) {
                answer.add(new ArrayList<Integer>(list));
            }
            return;
        }
        
        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            list.add(candidates[i]);
            combinationSum2(answer, list, seen, candidates, target - candidates[i], i + 1);
            list.remove(list.size() - 1);
        }
    }
}
