public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        // Collect all x positions and heights.
        List<int[]> heights = new ArrayList<>();
        for (int[] b: buildings) {
            int left = b[0];
            int right = b[1];
            int height = b[2];
            
            heights.add(new int[] { left, -height }); // negative height means building start
            heights.add(new int[] { right, height });
        }
        
        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        
        // Process for each x position.
        TreeMap<Integer, Integer> heightToCountMap = new TreeMap<>();
        heightToCountMap.put(0, 1);
        int prevHeight = 0;
        List<int[]> skyLine = new LinkedList<>();
        
        for (int[] h: heights) {
            int x = h[0];
            int height = h[1];
            
            if (height < 0) {
                // Add a new building.
                height = -height;
                Integer count = heightToCountMap.get(height);
                count = (count == null) ? 1 : count + 1;
                heightToCountMap.put(height, count);
            } else {
                // Remove an existing building.
                Integer count = heightToCountMap.get(height);
                if (count == 1) {
                    heightToCountMap.remove(height);
                }
                else {
                    heightToCountMap.put(height, count - 1);
                }
            }
            
            int currHeight = heightToCountMap.lastKey();
            if (prevHeight != currHeight) {
                skyLine.add(new int[] { x, currHeight });
                prevHeight = currHeight;
            }
        }
        
        return skyLine;
    }
}
