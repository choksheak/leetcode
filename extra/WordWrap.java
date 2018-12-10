/*
Word wrap:
Given a big string and a max column width, return a list of strings such that each string does not exceed the max width.
Implement justify alignment.
*/

import java.util.*;

public class WordWrap {
  public static List<String> wordWrap(String s, int maxWidth) {
    List<String> returnList = new ArrayList<>();
    StringBuilder line = new StringBuilder();
    String[] a = s.split("\\s+");
    
    for (String word: a) {
      int newColumn = line.length() + word.length()
          + (line.length() > 0 ? 1 : 0);

      if (newColumn > maxWidth) {
        returnList.add(line.toString());
        line.setLength(0);

        while (word.length() > maxWidth) {
          returnList.add(word.substring(0, maxWidth - 1) + "-");
          word = word.substring(maxWidth - 1);
        }
      } else if (line.length() > 0) {
        line.append(' ');
      }

      line.append(word);
    }

    if (line.length() > 0) {
      returnList.add(line.toString());
    }

    return returnList;
  }

  public static List<String> justifyAlign(String s, int maxWidth) {
    List<String> list = wordWrap(s, maxWidth);
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < list.size(); i++) {
      String line = list.get(i);

      if (line.indexOf(" ") < 0) {
        continue;
      }

      String[] words = line.split(" ");
      int numGaps = words.length - 1;
      
      int wordLengths = 0;
      for (String word: words) {
        wordLengths += word.length();
      }

      int extraSpaces = maxWidth - wordLengths - numGaps;
      if (extraSpaces == 0) {
        continue;
      }

      int extraSpacePerGap = extraSpaces / numGaps;
      int numGapsWithOneMoreSpace = extraSpaces % numGaps;

      sb.append(words[0]);

      for (int j = 1; j < words.length; j++) {
        int numSpaces = 1 + extraSpacePerGap
            + (j <= numGapsWithOneMoreSpace ? 1 : 0);

        for (int k = 0; k < numSpaces; k++) {
          sb.append(' ');
        }

        sb.append(words[j]);
      }

      list.set(i, sb.toString());
      sb.setLength(0);
    }

    return list;
  }

  public static void main(String[] args) {
    System.out.println(wordWrap("aa bbb ccc dd eeeee ffffffffffffff gg a b c d", 7));
    System.out.println(justifyAlign("aa bbb ccc dd eeeee fffffffffffff gg a b c d", 7));
    System.out.println(justifyAlign("aaaa b ccc dddddd e ff g hh iiiiiiiiiiii", 10));
  }
}