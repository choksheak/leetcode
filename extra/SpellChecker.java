/*
Spell-checking:
Given a hash set of known dictionary words, take a big string as input and return all the misspelt words. A word is any contiguous sequence of alphabets including hyphens (dashes) excluding all punctuation and digits. A word with a hyphen may or may not cross new lines. E.g. "user-\r\nfriendly" is a valid word, but "user\r\n-friendly" is two invalid words "user" and "-friendly" (optional hyphen, bonus points for ignoring hyphens).

Restrictions:
(a) Do not use regex in your solution.

Steps:
(a) List the condition for when a group of strings is a word or not.
(b) Support spell-check with words containing dashes crossing new lines.
(c) Describe how you would improve the runtime performance of your code.
*/

import java.util.*;
import java.util.stream.*;

public class SpellChecker {

  private final Set<String> validWords;

  public SpellChecker(Set<String> validWords) {
    this.validWords = validWords.stream()
        .map(w -> w.toLowerCase()).collect(Collectors.toSet());
  }

  private static boolean isWordChar(char c) {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c == '-');
  }

  private void digestWord(StringBuilder sb, Set<String> mispeltWords) {
    if (sb.length() > 0) {
      String word = sb.toString();
      if (!validWords.contains(word.toLowerCase())) {
        mispeltWords.add(word);
      }
      sb.setLength(0);
    }
  }

  public Set<String> findMispeltWords(String blob) {
    Set<String> mispeltWords = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    final char NONE = '\0';
    char lastWordChar = NONE;

    for (int i = 0; i < blob.length(); i++) {
      char c = blob.charAt(i);

      if (c == '\n' || c == '\r') {
        if (lastWordChar != '-') {
          digestWord(sb, mispeltWords);
          lastWordChar = NONE;
        }
      } else if (isWordChar(c)) {
        // If word starts with a hyphen, just ignore the hyphen completely.
        if (c != '-' || sb.length() > 0) {
          sb.append(c);
        }
        lastWordChar = c;
      } else {
        digestWord(sb, mispeltWords);
        lastWordChar = NONE;
      }
    }

    digestWord(sb, mispeltWords);
    return mispeltWords;
  }

  private static void test(
      List<String> validWords,
      String blob,
      String expected) {
    SpellChecker spellChecker = new SpellChecker(new HashSet<>(validWords));

    Set<String> mispeltWords = spellChecker.findMispeltWords(blob);

    String actual = new TreeSet<>(mispeltWords).toString();

    System.out.println("Expect = " + expected);
    System.out.println("Actual = " + actual);

    System.out.println(actual.equals(expected) ? "Correct!" : "Wrong!");

    System.out.println();
  }

  public static void main(String[] args) {
    test(Arrays.asList(), "", "[]");

    test(Arrays.asList(), "a", "[a]");

    test(Arrays.asList("a"), "a", "[]");

    test(
        Arrays.asList("user-friendly", "DOG", "is", "a", "my"),
        "- -- -My dog is\na Nice\r\n and user-\r\n\n\r\rfriendly BEAST.",
        "[BEAST, Nice, and]");
  }
}
