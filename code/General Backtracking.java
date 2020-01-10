import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * A general approach to backtracking questions in Java (Subsets, Permutations, Combination Sum,
 * Palindrome Partitioning)
 *
 * <p>This structure might apply to many other backtracking questions, but here I am just going to
 * demonstrate Subsets, Permutations, and Combination Sum.
 */
public class GeneralBacktracking {

  // Subsets : https://leetcode.com/problems/subsets/
  // --------------------------------------------------------------------------------------------

  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> answer = new ArrayList<>();
    // Arrays.sort(nums); // return answer in sorted order
    recurseSubsets(answer, new ArrayList<>(), nums, 0);
    return answer;
  }

  private static void recurseSubsets(
      List<List<Integer>> answer, ArrayList<Integer> currentAnswer, int[] nums, int startIndex) {
    // Always add all subsets to the answer.
    answer.add(new ArrayList<>(currentAnswer)); // clone

    for (int i = startIndex; i < nums.length; i++) {
      currentAnswer.add(nums[i]);
      recurseSubsets(answer, currentAnswer, nums, i + 1); // i+1: don't reuse nums[i]
      currentAnswer.remove(currentAnswer.size() - 1);
    }
  }

  // Subsets II (contains duplicates) : https://leetcode.com/problems/subsets-ii/
  // --------------------------------------------------------------------------------------------

  // Alternative: We can simply remove all duplicates from the start by putting nums into a set.
  public static List<List<Integer>> subsetsWithDuplicates(int[] nums) {
    List<List<Integer>> answer = new ArrayList<>();
    Arrays.sort(nums); // sort to remove duplicates later
    recurseSubsetsWithDuplicates(answer, new ArrayList<>(), nums, 0);
    return answer;
  }

  private static void recurseSubsetsWithDuplicates(
      List<List<Integer>> answer, List<Integer> currentAnswer, int[] nums, int startIndex) {
    // Always add all subsets to the answer.
    answer.add(new ArrayList<>(currentAnswer)); // clone

    for (int i = startIndex; i < nums.length; i++) {
      if (i > startIndex && nums[i] == nums[i - 1]) {
        continue; // skip duplicates
      }

      currentAnswer.add(nums[i]);
      recurseSubsetsWithDuplicates(answer, currentAnswer, nums, i + 1); // i+1: don't reuse nums[i]
      currentAnswer.remove(currentAnswer.size() - 1);
    }
  }

  // Permutations : https://leetcode.com/problems/permutations/
  // --------------------------------------------------------------------------------------------

  public static List<List<Integer>> permutate(int[] nums) {
    List<List<Integer>> answer = new ArrayList<>();
    // Arrays.sort(nums); // return answer in sorted order
    recursePermutate(answer, new LinkedHashSet<>(), nums); // need LinkedHashSet for quick lookup
    return answer;
  }

  private static void recursePermutate(
      List<List<Integer>> answer, LinkedHashSet<Integer> currentAnswer, int[] nums) {
    if (currentAnswer.size() == nums.length) { // only add when all elements are present
      answer.add(new ArrayList<>(currentAnswer)); // clone
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (currentAnswer.contains(nums[i])) { // using LinkedHashSet for quick lookup
        continue; // element already exists, skip
      }

      currentAnswer.add(nums[i]);
      recursePermutate(answer, currentAnswer, nums);
      currentAnswer.remove(currentAnswer.size() - 1);
    }
  }

  // Permutations II (contains duplicates) : https://leetcode.com/problems/permutations-ii/
  // --------------------------------------------------------------------------------------------

  // Alternative: We can simply remove all duplicates from the start by putting nums into a set.
  public static List<List<Integer>> permutateUnique(int[] nums) {
    List<List<Integer>> answer = new ArrayList<>();
    Arrays.sort(nums); // sort to remove duplicates later
    recursePermutateUnique(answer, new ArrayList<>(), nums, new boolean[nums.length]);
    return answer;
  }

  private static void recursePermutateUnique(
      List<List<Integer>> answer, List<Integer> currentAnswer, int[] nums, boolean[] used) {
    if (currentAnswer.size() == nums.length) {
      answer.add(new ArrayList<>(currentAnswer)); // clone
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
        continue; // skip duplicates
      }

      used[i] = true;
      currentAnswer.add(nums[i]);

      recursePermutateUnique(answer, currentAnswer, nums, used);

      used[i] = false;
      currentAnswer.remove(currentAnswer.size() - 1);
    }
  }

  // Combination Sum : https://leetcode.com/problems/combination-sum/
  // --------------------------------------------------------------------------------------------

  public static List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> answer = new ArrayList<>();
    // Arrays.sort(nums); // return answer in sorted order
    recurseCombinationSum(answer, new ArrayList<>(), nums, target, 0);
    return answer;
  }

  private static void recurseCombinationSum(
      List<List<Integer>> answer,
      List<Integer> currentAnswer,
      int[] nums,
      int remainingTarget,
      int startIndex) {
    if (remainingTarget < 0) {
      return;
    }

    if (remainingTarget == 0) {
      answer.add(new ArrayList<>(currentAnswer)); // clone
      return;
    }

    for (int i = startIndex; i < nums.length; i++) {
      currentAnswer.add(nums[i]);
      recurseCombinationSum(
          answer,
          currentAnswer,
          nums,
          remainingTarget - nums[i],
          i); // not i + 1 because we can reuse same elements
      currentAnswer.remove(currentAnswer.size() - 1);
    }
  }

  // Combination Sum II (can't reuse same element) :
  // https://leetcode.com/problems/combination-sum-ii/
  // --------------------------------------------------------------------------------------------

  public static List<List<Integer>> combinationSum2(int[] nums, int target) {
    List<List<Integer>> answer = new ArrayList<>();
    // Arrays.sort(nums); // return answer in sorted order
    recurseCombinationSum2(answer, new ArrayList<>(), nums, target, 0);
    return answer;
  }

  private static void recurseCombinationSum2(
      List<List<Integer>> answer,
      List<Integer> currentAnswer,
      int[] nums,
      int remainingTarget,
      int startIndex) {
    if (remainingTarget < 0) {
      return;
    }

    if (remainingTarget == 0) {
      answer.add(new ArrayList<>(currentAnswer)); // clone
      return;
    }

    for (int i = startIndex; i < nums.length; i++) {
      if (i > startIndex && nums[i] == nums[i - 1]) {
        continue; // skip duplicates
      }

      currentAnswer.add(nums[i]);
      recurseCombinationSum2(answer, currentAnswer, nums, remainingTarget - nums[i], i + 1);
      currentAnswer.remove(currentAnswer.size() - 1);
    }
  }

  // Palindrome Partitioning : https://leetcode.com/problems/palindrome-partitioning/
  // --------------------------------------------------------------------------------------------

  public static List<List<String>> partitionByPalindromes(String s) {
    List<List<String>> answer = new ArrayList<>();
    recursePartitionByPalindromes(answer, new ArrayList<>(), s, 0);
    return answer;
  }

  private static void recursePartitionByPalindromes(
      List<List<String>> answer, List<String> currentAnswer, String s, int startIndex) {
    if (startIndex == s.length()) {
      answer.add(new ArrayList<>(currentAnswer)); // clone
      return;
    }

    for (int i = startIndex; i < s.length(); i++) {
      if (isPalindrome(s, startIndex, i)) {
        currentAnswer.add(s.substring(startIndex, i + 1));
        recursePartitionByPalindromes(answer, currentAnswer, s, i + 1);
        currentAnswer.remove(currentAnswer.size() - 1);
      }
    }
  }

  private static boolean isPalindrome(String s, int low, int high) {
    while (low < high) {
      if (s.charAt(low++) != s.charAt(high--)) {
        return false;
      }
    }
    return true;
  }
}
