/*
Parse English number:
Given a list of words for an English number, parse it into a long integer. The biggest unit to support is one hundred million.
E.g. "thirteen thousand and two hundred" -> 13200
*/

import java.util.*;

public class ParseEnglishNumber {

  private static final Map<String, Integer> ENGLISH_NUMBERS
      = Collections.unmodifiableMap(new HashMap<>() {{
        // Note: for the purpose of this question, "zero" is not a valid number.
        put("one", 1);
        put("two", 2);
        put("three", 3);
        put("four", 4);
        put("five", 5);
        put("six", 6);
        put("seven", 7);
        put("eight", 8);
        put("nine", 9);
        put("ten", 10);
        put("eleven", 11);
        put("twelve", 12);
        put("thirteen", 13);
        put("fourteen", 14);
        put("fifteen", 15);
        put("sixteen", 16);
        put("seventeen", 17);
        put("eighteen", 18);
        put("nineteen", 19);
        put("twenty", 20);
        put("thirty", 30);
        put("forty", 40);
        put("fifty", 50);
        put("sixty", 60);
        put("seventy", 70);
        put("eighty", 80);
        put("ninety", 90);
      }});

  private static final Map<String, Integer> ENGLISH_PLACE_VALUES
      = Collections.unmodifiableMap(new HashMap<>() {{
        put("hundred", 100);
        put("thousand", 1000);
        put("million", 1000000);
      }});

  public static long parseEnglishNumber(String s) {
    String[] words = s.trim().toLowerCase().split("[\\s-]+");
    int value = 0;
    int currentValue = 0;
    
    for (int i = 0; i < words.length; i++) {
      String word = words[i];

      if (word.equals("and")) {
        if (i == 0 || !isEnglishPlaceValue(words[i - 1])) {
          throw new NumberFormatException("Misplaced and"); 
        }
        if (i < words.length - 1 && !isEnglishNumber(words[i + 1])) {
          throw new NumberFormatException(
              "`and' and " + words[i + 1] + " cannot appear together"); 
        }
      } else if (isEnglishNumber(word)) {
        if (i < words.length - 1 && isEnglishNumber(words[i + 1])) {
          if (!englishNumberAllowsNextNumber(word)
              || !englishNumberAllowsPreviousNumber(words[i + 1])) {
            throw new NumberFormatException(
                word + " and " + words[i + 1] + " cannot appear together");
          }
        }

        currentValue += getEnglishAsInteger(word);

      } else if (isEnglishPlaceValue(word)) {
        if (currentValue == 0) {
          throw new NumberFormatException("Misplaced place value");
        }

        // Handle "hundred thousand".
        int place = getEnglishPlaceValue(word);
        if (i < words.length - 1 && isEnglishPlaceValue(words[i + 1])) {
          if (!word.equals("hundred") || words[i + 1].equals("hundred")) {
            throw new NumberFormatException(
                word + " and " + words[i + 1] + " cannot appear together");
          }
          place *= getEnglishPlaceValue(words[++i]);
        }

        value += currentValue * place;
        currentValue = 0;
      }
    }

    return value;
  }

  private static boolean isEnglishNumber(String s) {
    return ENGLISH_NUMBERS.containsKey(s);
  }

  private static int getEnglishAsInteger(String s) {
    return ENGLISH_NUMBERS.get(s);
  }

  private static boolean englishNumberAllowsNextNumber(String s) {
    return ENGLISH_NUMBERS.get(s) >= 20;
  }

  private static boolean englishNumberAllowsPreviousNumber(String s) {
    return ENGLISH_NUMBERS.get(s) <= 9;
  }

  private static boolean isEnglishPlaceValue(String s) {
    return ENGLISH_PLACE_VALUES.containsKey(s);
  }

  private static int getEnglishPlaceValue(String s) {
    return ENGLISH_PLACE_VALUES.get(s);
  }

  public static void main(String[] args) {
    System.out.println(parseEnglishNumber("thirteen thousand and two hundred"));
    System.out.println(parseEnglishNumber("five hundred million forty one thousand and two"));
  }
}
