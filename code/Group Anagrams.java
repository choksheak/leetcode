class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s: strs) {
            char[] a = s.toCharArray();
            Arrays.sort(a);
            String t = new String(a);
            
            List<String> list = map.get(t);
            
            if (list == null) {
                list = new ArrayList<String>();
                map.put(t, list);
            }
            
            list.add(s);
        }
        
        return new ArrayList<>(map.values());
    }
}
