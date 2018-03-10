import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution {
    public String minWindow(String s, String t) {
        // Form map of chars in t to count.
        Map<Character, Integer> tChars = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer count = tChars.get(c);
            tChars.put(c, count != null ? count + 1 : 1);
        }
        
        Map<Character, TreeSet<Integer>> seenCharIndexes = new HashMap<>();
        TreeSet<Integer> seenIndexes = new TreeSet<>();
        
        int minWinStart = 0;
        int minWinEnd = -1;
        int minWinSize = s.length() + 1;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (!tChars.containsKey(c)) {
                continue;
            }
            
            TreeSet<Integer> charIndexes = seenCharIndexes.get(c);
            int count = (charIndexes != null) ? charIndexes.size() + 1 : 1;
            
            // If we have too many of the same char c, remove the first c within the current window.
            if (count > tChars.get(c)) {
            	int index = charIndexes.pollFirst();
            	seenIndexes.remove(index);
            }
            else {
            	if (charIndexes == null) {
            		charIndexes = new TreeSet<Integer>();
            		seenCharIndexes.put(c, charIndexes);
            	}
            }

            charIndexes.add(i);
            seenIndexes.add(i);

            if (seenIndexes.size() == t.length()) {
                // Track minimum window.
                int min = seenIndexes.pollFirst(); // remove
                int size = i - min + 1;

                if (size < minWinSize) {
                    minWinStart = min;
                    minWinEnd = i;
                    minWinSize = size;
                }

                // Remove char at start index from seenCount.
                c = s.charAt(min);
                seenCharIndexes.get(c).pollFirst();
            }
        }
        
        return minWinEnd == -1 ? "" : s.substring(minWinStart, minWinEnd + 1);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String answer = s.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd");
        System.out.println(answer);

        if (!answer.equals("abbbbbcdd")) {
            System.out.println("Wrong answer");
        }
    }
}
