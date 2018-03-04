class MedianFinder {

    // maxQueue contains the smallest numbers, minQueue contains the largest numbers.
    private PriorityQueue<Integer> maxQueue = new PriorityQueue(Collections.reverseOrder());
    private PriorityQueue<Integer> minQueue = new PriorityQueue();
    
    /** initialize your data structure here. */
    public MedianFinder() {
    }
    
    public void addNum(int num) {
        maxQueue.add(num);
        minQueue.add(maxQueue.poll());
        
        if (maxQueue.size() < minQueue.size()) {
            maxQueue.add(minQueue.poll());
        }
    }
    
    public double findMedian() {
        return (maxQueue.size() == minQueue.size())
            ? (maxQueue.peek() + minQueue.peek()) /  2.0
            : maxQueue.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
