class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] minTime = new int[N + 1];
        
        Arrays.fill(minTime, Integer.MAX_VALUE);
        minTime[K] = 0;
        stack.add(K);
        
        do {
            int node = stack.removeLast();
            
            for (int[] edge: times) {
                int source = edge[0];
                
                if (source == node) {
                    int target = edge[1];
                    int time = edge[2];
                    
                    int minTimeFromSource = minTime[source] + time;
                    
                    // Update target node min time.
                    int oldTime = minTime[target];
                    minTime[target] = Math.min(minTimeFromSource, minTime[target]);
                    
                    // Add target if min time changed.
                    if (oldTime != minTime[target]) {
                        stack.add(target);
                    }
                }
            }
        }
        while (!stack.isEmpty());
        
        // Return max time as answer.
        int max = 0;
        
        for (int node = 1; node <= N; node++) {
            // If node is unreachable, return -1.
            if (minTime[node] == Integer.MAX_VALUE) {
                return -1;
            }
            
            max = Math.max(max, minTime[node]);
        }
        
        return max;
    }
}
