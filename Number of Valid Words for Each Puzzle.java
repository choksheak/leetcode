/*
With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:

    word contains the first letter of puzzle.
    For each letter in word, that letter is in puzzle.
    For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage";
    while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).

Return an array answer, where answer[i] is the number of words in the given word list words that are
valid with respect to the puzzle puzzles[i]. 
*/

// Main trick: how to generate all sub-combinations of a bitmask:
//   subMask = (subMask - 1) & puzzleMask;
class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        // Many words will get mapped to the same bitmask, so just keep a count per mask.
        Map<Integer, Integer> wordMasks = new HashMap<>(words.length);
        for (int i = 0; i < words.length; i++) {
            int mask = getBitMask(words[i]);
            wordMasks.put(mask, wordMasks.getOrDefault(mask, 0) + 1);
        }
        
        List<Integer> counts = new ArrayList<>();
        for (String puzzle : puzzles) {
            int count = 0;
            int puzzleMask = getBitMask(puzzle);
            
            // The key here is to iterate through all the subsets of puzzleMask,
            // because if we iterate through all the words, then we will get the
            // time limit exceeded error.
            int subMask = puzzleMask;
            int firstCharMask = 1 << (puzzle.charAt(0) - 'a');
            
            while (true) {
                if ((subMask & firstCharMask) != 0) {
                    count += wordMasks.getOrDefault(subMask, 0);
                }
                
                // Exhausted all possible submasks, so done.
                if (subMask == 0) {
                    break;
                }
                
                // Iterate to the next possible submask.
                subMask = (subMask - 1) & puzzleMask;
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
