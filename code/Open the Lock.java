class Solution {
    private static final String START = "0000";
    
    public int openLock(String[] deadends, String target) {
        Set<String> seen = Arrays.stream(deadends).collect(Collectors.toSet());
        
        if (seen.contains(START)) {
            return -1;
        }
        seen.add(START);
        
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(START);
        queue.addLast(null);        

        int dist = 0;

        do {
            String s = queue.removeFirst();
            
            // When we hit a null, it means we have moved to the next depth.
            if (s == null) {
                dist++;
                
                // Only add a null if the queue is not empty.
                if (!queue.isEmpty()) {
                    queue.addLast(null);
                }
                
                continue;
            }
            
            if (s.equals(target)) {
                return dist;
            }
            
            for (int i = 0; i < 4; i++) {
                for (int j = -1; j <= 1; j += 2) {
                    int d = (s.charAt(i) - '0' + j + 10) % 10;
                    String t = s.substring(0, i) + d + s.substring(i + 1);
                    if (!seen.contains(t)) {
                        queue.addLast(t);
                        seen.add(t);
                    }
                }
            }
        }
        while (!queue.isEmpty()); 
            
        return -1;
    }
}
