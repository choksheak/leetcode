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
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals;
        }
        
        // Sort by interval start.
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
        
        List<Interval> list = new ArrayList<>();
        Interval prev = intervals.get(0);
        
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            
            if (curr.start <= prev.end) {
                prev.end = Math.max(prev.end, curr.end);
            }
            else {
                list.add(prev);
                prev = curr;
            }
        }
        
        list.add(prev);
        return list;
    }
}
