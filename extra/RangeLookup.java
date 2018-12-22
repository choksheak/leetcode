/*
HARD: Range Lookup:
Given a list of strings in sorted order, implement a data lookup algorithm
which will take a value range (min, max) and return all the values within this
range, inclusive of min and max. If either or both of min and max are null,
then that means the bound is missing.
*/

import java.util.*;

public class RangeLookup {

  public static List<String> rangeLookup(List<String> list, String min,
      String max) {
    if (list.isEmpty()) {
      return list;
    }

    int minIndex;

    if (min == null) {
      minIndex = 0;
    } else {
      int[] a = findNearestIndexesForValue(list, min);
        // Get the same or bigger value.
        minIndex = list.get(a[0]).equals(min) ? a[0] : a[1];
    }

    int maxIndex;
    
    if (max == null) {
      maxIndex = list.size() - 1;
    } else {
      int[] a = findNearestIndexesForValue(list, max);
      // Get the same or smaller value.
      maxIndex = list.get(a[1]).equals(max) ? a[1] : a[0];
    }

    return list.subList(minIndex, maxIndex + 1);
  }

  private static int[] findNearestIndexesForValue(List<String> list,
      String value) {
    int lo = 0;
    int hi = list.size() - 1;

    // Handle edge cases first.
    if (list.get(lo).compareTo(value) >= 0) { // lo >= value
      return new int[] {lo, lo};
    }

    if (list.get(hi).compareTo(value) <= 0) { // hi <= value
      return new int[] {hi, hi};
    }

    // We just determined that first < value < last.
    int mid = lo + (hi - lo) / 2; // prevent integer overflow
    
    while (lo < hi) {
      int cmp = list.get(mid).compareTo(value);

      if (cmp == 0) { // mid == value
        break;
      }

      if (cmp < 0) { // mid < value
        if (mid + 1 > hi || list.get(mid + 1).compareTo(value) > 0) {
          break;
        }
        lo = mid + 1;
      } else /* cmp > 0 => mid > value */ {
        hi = mid - 1;
      }

      mid = lo + (hi - lo) / 2;
    }

    // Make sure returned indexes covers the whole range of duplicate matches.
    lo = hi = mid;

    while (list.get(lo - 1).compareTo(value) == 0) { // lo - 1 == value
      lo--;
    }
    if (list.get(lo).compareTo(value) > 0) { // lo >= value
      lo--;
    }
    while (list.get(hi + 1).compareTo(value) == 0) { // hi + 1 <= value
      hi++;
    }
    if (list.get(hi).compareTo(value) < 0) { // hi <= value
      hi++;
    }

    return new int[] {lo, hi};
  }

  public static void main(String[] args) {
    System.out.println(
      rangeLookup(Arrays.asList("a", "c", "e", "f", "i", "j"), "d", "h"));
    System.out.println(
      rangeLookup(Arrays.asList("a", "c", "e", "f", "i", "j"), "e", "j"));
    System.out.println(
      rangeLookup(Arrays.asList("c", "e", "f", "i"), "b", null));
    System.out.println(
      rangeLookup(Arrays.asList("a", "b", "b", "c", "c", "j"), "b", "c"));
  }
}
