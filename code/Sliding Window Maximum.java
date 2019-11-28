// Given an array nums, there is a sliding window of size k which is moving from
// the very left of the array to the very right. You can only see the k numbers
// in the window. Each time the sliding window moves right by one position.
// Return the max sliding window.

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) return new int[0];
        
        int n = nums.length - k + 1;
        int[] a = new int[n];
        int ai = 0;
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x);
        
        for (int i = 0; i < k - 1; i++) {
            maxHeap.add(nums[i]);
        }
        
        for (int i = k - 1; i < nums.length; i++) {
            maxHeap.add(nums[i]);
            
            a[ai++] = maxHeap.peek();
            
            maxHeap.remove(nums[i - k + 1]);
        }
        
        return a;
    }
}
