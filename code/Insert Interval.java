/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>(intervals.size() + 1);
        int i = 0;        
        
        // Add everything before newInterval.
        for (; i < intervals.size() && intervals.get(i).end < newInterval.start; i++) {
            result.add(intervals.get(i));
        }
        
        // Clone to prevent overwriting input argument.
        newInterval = new Interval(newInterval.start, newInterval.end);
        
        // Add everything overlapping with newInterval.
        for (; i < intervals.size() && intervals.get(i).start <= newInterval.end; i++) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
        }
        
        result.add(newInterval);

        // Add everything after newInterval.
        for (; i < intervals.size(); i++) {
            result.add(intervals.get(i));
        }

        return result;
    }
}
