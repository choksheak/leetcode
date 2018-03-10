class Solution {
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        
        for (int i = 0; i < heights.length; i++) {
            // Reduce whenever we hit a smaller height.
            while (!stack.isEmpty() && heights[stack.getLast()] > heights[i]) {
                int height = heights[stack.removeLast()];
                int width = (stack.isEmpty() ? i : i - 1 - stack.getLast());
                maxArea = Math.max(maxArea, height * width);
            }
            
            // Shift whenever the heights are going up or remaining the same.
            stack.addLast(i);
        }
        
        // Reduce for all remaining heights in the stack.
        while (!stack.isEmpty()) {
            int height = heights[stack.removeLast()];
            int width = (stack.isEmpty() ? heights.length : heights.length - 1 - stack.getLast());
            maxArea = Math.max(maxArea, height * width);
        }
        
        return maxArea;
    }
}
