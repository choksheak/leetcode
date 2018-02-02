class Solution {
    // Model answer uses dynamic programming, but this also works.
    public int findLength(int[] A, int[] B) {
        // Cache the indexes of all integers in B.
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < B.length; i++) {
            map.computeIfAbsent(B[i], j -> new ArrayList<Integer>()).add(i);
        }
        
        int len = 0;
        
        for (int i = 0; i < A.length; i++) {
            List<Integer> list = map.get(A[i]);
            
            if (list == null) {
                continue;
            }
            
            for (int j: list) {
                // Start matching after len.
                int k = len;
                while (i + k < A.length && j + k < B.length && A[i + k] == B[j + k]) {
                    k++;
                }
                
                // If we matched anything after len, match all before len.
                if (k > len) {
                    int m = 1;
                    while (m < len && A[i + m] == B[j + m]) {
                        m++;
                    }
                    if (m >= len) {
                        len = k;
                    }
                }
            }
        }
        
        return len;
    }
}
