/*
Quote Conversion:
Given a string that represents a program in a programming language, convert the
string quotes into either single quotes or double quotes depending on the
input. Don't worry about escape sequences other than single and double quotes.
(a) Don't worry about handling comments and unterminated strings.
(b) Worry about unterminated strings.
(c) For each string, find the quote type for it that will produce the shortest
    string.
*/

import java.util.Objects;

public class StringQuoteConverter {
  // Using enum will force the user to provide the correct argument with
  // compile-time type checking.
  public static enum QuoteType {
    SINGLE,
    DOUBLE,
  }
  
  public static String convertQuotes(String s, QuoteType toQuoteType) {
    // Null check is optional here.
    if (s == null || s.isEmpty()) {
      return s;
    }
    
    // Add a few chars to the length in case we need to add some '\\'.
    StringBuilder sb = new StringBuilder(s.length() + 5);
    
    // Space means none, can optionally use a constant.
    char startQuote = ' ';
    
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      
      switch (c) {
      	case '"':
          if (toQuoteType == QuoteType.DOUBLE) {
            if (startQuote == '"') {
              startQuote = ' ';
            } else {
              startQuote = '"';
            }
            sb.append('"');
          } else {
            if (startQuote == '"') {
              startQuote = ' ';
              sb.append('\'');
            } else if (startQuote == '\'') {
              sb.append('"');
            } else {
              startQuote = '"';
              sb.append('\'');
            }
          }
          break;
        case '\'':
          // We might be able to refactor this code with the above, but this is
          // not important for now.
          if (toQuoteType == QuoteType.SINGLE) {
            if (startQuote == '\'') {
              startQuote = ' ';
            } else {
              startQuote = '\'';
            }
            sb.append('\'');
          } else {
            if (startQuote == '\'') {
              startQuote = ' ';
              sb.append('"');
            } else if (startQuote == '"') {
              sb.append('\'');
            } else {
              startQuote = '\'';
              sb.append('"');
            }
          }
          break;
        case '\\':
          if (startQuote == ' ') {
            sb.append('\\');
            break;
          }

          // Check next character.
          i++;
          if (i >= s.length()) {
            sb.append('\\');
            break;
          }

          char d = s.charAt(i);
          switch (d) {
            case '"':
              if (toQuoteType == QuoteType.SINGLE) {
                sb.append('"');
              } else {
                sb.append('\\').append('"');
              }
              break;
            case '\'':
              if (toQuoteType == QuoteType.SINGLE) {
                sb.append('\\').append('\'');
              } else {
                sb.append('\'');
              }
              break;
            default:
              sb.append('\\').append(d);
          }
          break;
        default:
          sb.append((char) c);
      }
    }
    
    return sb.toString();
  }
  
  public static void test(String s, QuoteType toQuoteType, String expected) {
    String actual = convertQuotes(s, toQuoteType);
    if (!Objects.equals(actual, expected)) {
      throw new AssertionError("Test failed:\nExpect = " + expected + "\nActual = " + actual);
    }
    System.out.println("Pass: (" + s + ", " + toQuoteType + ") -> " + actual);
  }

  public static void main(String[] args) {
    test("a", QuoteType.SINGLE, "a");
    test("a'", QuoteType.SINGLE, "a'");
    test("'a", QuoteType.SINGLE, "'a");
    test("'a'", QuoteType.SINGLE, "'a'");
    test("'a'", QuoteType.DOUBLE, "\"a\"");
    test("a'", QuoteType.DOUBLE, "a\"");
    test("'a", QuoteType.DOUBLE, "\"a");
    test("'a\\''", QuoteType.DOUBLE, "\"a'\"");
    test("\"a\\\"\"", QuoteType.DOUBLE, "\"a\\\"\"");
    // More test cases can be added to cover all code paths ...
  }
}
