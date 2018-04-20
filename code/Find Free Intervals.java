import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    public static class Interval {
        public int start, end;
        
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public String toString() {
            return "(" + start + "," + end + ")";
        }
    }
    
    private static List<Interval> findFreeIntervals(List<List<Interval>> intervals) {
        PriorityQueue<Interval> sortedIntervals = new PriorityQueue<>((i, j) -> i.start - j.start);
        
        // Insert all the intervals into a sorted collection.
        for (List<Interval> list: intervals) {
            for (Interval i: list) {
                sortedIntervals.add(i);
            }
        }
        
        if (sortedIntervals.size() == 0) {
            return new ArrayList<Interval>();
        }
        
        // Merge them.
        List<Interval> merged = new ArrayList<>();
        Interval in = sortedIntervals.remove();
        int lastStart = in.start;
        int lastEnd = in.end;
        
        
        while (!sortedIntervals.isEmpty()) {
            in = sortedIntervals.remove();
            
            if (in.start <= lastEnd) {
                // Merge.
                lastEnd = Math.max(lastEnd, in.end);
            }
            else {
                // Start new interval.
                merged.add(new Interval(lastStart, lastEnd));
                lastStart = in.start;
                lastEnd = in.end;
            }
        }
        
        // Always add the current interval to the end.
        merged.add(new Interval(lastStart, lastEnd));
        
        // Flip the interval.
        List<Interval> flipped = new ArrayList<>();     
        lastEnd = merged.get(0).end;
        
        if (merged.get(0).start > 0) {
            flipped.add(new Interval(0, merged.get(0).start));
        }
        
        for (int i = 1; i < merged.size(); i++) {
            in = merged.get(i);
            flipped.add(new Interval(lastEnd, in.start));
            lastEnd = in.end;
        }
        
        return flipped;
    }
    
    public static void main(String[] args) {
        
        List<List<Interval>> intervals = new ArrayList<>();
        intervals.add(Arrays.asList(new Interval(30, 60), new Interval(120, 150)));
        intervals.add(Arrays.asList(new Interval(45, 75)));
        intervals.add(Arrays.asList(new Interval(180, 200)));
        
        List<Interval> answer = findFreeIntervals(intervals);
        
        System.out.println(answer);
    }
}
