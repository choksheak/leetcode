/*
https://leetcode.com/problems/largest-rectangle-in-histogram/

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

[Image: https://assets.leetcode.com/uploads/2018/10/12/histogram.png]

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

[Image: https://assets.leetcode.com/uploads/2018/10/12/histogram_area.png]

The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example:

Input: [2,1,5,6,2,3]
Output: 10
*/

class Solution {
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stackOfIndices = new LinkedList<>();
        int maxArea = 0;
        int n = heights.length;
        
        for (int i = 0; i < n; i++) {
            // Reduce whenever we hit a smaller height.
            while (!stackOfIndices.isEmpty() && heights[stackOfIndices.getLast()] > heights[i]) {
                int height = heights[stack.removeLast()];
                int width = (stackOfIndices.isEmpty() ? i : i - stackOfIndices.getLast() - 1);
                int area = height * width; // area between last two buildings in stack
                maxArea = Math.max(maxArea, area);
            }
            
            // Shift whenever the heights are going up or remaining the same.
            stackOfIndices.addLast(i);
        }
        
        // Reduce for all remaining heights in the stack.
        while (!stackOfIndices.isEmpty()) {
            int height = heights[stackOfIndices.removeLast()];
            int width = (stack.isEmpty() ? n : n - stackOfIndices.getLast() - 1);
            int area = height * width; // area from last building to rightmost edge n
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }
}
