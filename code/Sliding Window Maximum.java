/*
Given an array nums, there is a sliding window of size k which is moving from
the very left of the array to the very right. You can only see the k numbers
in the window. Each time the sliding window moves right by one position.
Return the max sliding window.
*/

// This is the common optimal solution which is O(N).
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) {
            return new int[0];
        }
        
        int[] answer = new int[n - k + 1];
        int answerIndex = 0;
        LinkedList<Integer> window = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            // Remove numbers that fell out from the left side.
            while (!window.isEmpty() && window.getFirst() <= i - k) {
                window.removeFirst();
            }
            
            // Remove any numbers from the right side that are smaller or equal to
            // the current number, since they will not affect the window maximum
            // after we add the current number.
            while (!window.isEmpty() && nums[window.getLast()] <= nums[i]) {
                window.removeLast();
            }
            
            // Add current index to right side of window.
            window.addLast(i);
            
            // Skip first k-1 numbers.
            if (i >= k - 1) {
                // The leftmost number in the window must be the current largest
                // number, because if this were not true, then we would already
                // have removed it earlier.
                answer[answerIndex++] = nums[window.getFirst()];
            }
        }
        
        return answer;
    }
}

// Non-optimal O(N log K) solution using heap.
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
