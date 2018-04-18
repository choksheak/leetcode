class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        combine(answer, new ArrayList<Integer>(), 1, n, k);
        return answer;
    }
    
    private void combine(List<List<Integer>> answer, List<Integer> list, int start, int n, int k) {
        if (k == 0) {
            answer.add(new ArrayList<Integer>(list));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            list.add(i);
            combine(answer, list, i + 1, n, k - 1);
            list.remove(list.size() - 1);
        }
    }
}
