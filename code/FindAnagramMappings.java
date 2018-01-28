class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        // Build the hash mapping of B.
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < B.length; i++) {
            map.put(B[i], i);
        }
        
        // Form a new array with the answer.
        int[] a = new int[A.length];
        
        for (int i = 0; i < a.length; i++) {
            a[i] = map.get(A[i]);
        }
        
        return a;
    }
}
