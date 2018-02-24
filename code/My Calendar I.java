class MyCalendar {

    public MyCalendar() {
    }
    
    public boolean book(int start, int end) {
        Integer floorStart = map.floorKey(start);
        
        if (floorStart != null && map.get(floorStart) > start) {
            return false;
        }
        
        Integer ceilingStart = map.ceilingKey(start);
        
        if (ceilingStart != null && ceilingStart < end) {
            return false;
        }
        
        map.put(start, end);
        return true;
    }
    
    private TreeMap<Integer, Integer> map = new TreeMap<>();
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
 
