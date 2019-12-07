/*
With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:

    word contains the first letter of puzzle.
    For each letter in word, that letter is in puzzle.
    For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage";
    while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).

Return an array answer, where answer[i] is the number of words in the given word list words that are
valid with respect to the puzzle puzzles[i]. 
*/

// This solution is correct, but gets the Time Limit Exceeded error in leetcode.
class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int[] wordMasks = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            wordMasks[i] = getBitMask(words[i]);
        }
        
        List<Integer> counts = new ArrayList<>();
        for (String puzzle : puzzles) {
            int count = 0;
            int puzzleMask = getBitMask(puzzle);

            for (int mask : wordMasks) {
                int firstCharMask = 1 << (puzzle.charAt(0) - 'a');
                if ((mask & firstCharMask) != 0) {
                    if ((puzzleMask & mask) == mask) {
                        count++;
                    }
                }
            }
            
            counts.add(count);
        }
        
        return counts;
    }
    
    private static int getBitMask(String word) {
        int mask = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            mask |= 1 << (word.charAt(i) - 'a');
        }
        return mask;
    }
}
