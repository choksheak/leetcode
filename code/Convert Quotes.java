public class Solution {

    private static String replaceQuotes(String content, char targetChar) {
        if (targetChar != '"' && targetChar != '\'') {
            throw new IllegalArgumentException();
        }
        
        int index = 0;
        StringBuilder output = new StringBuilder();
        
        // 1. Multi-line comments.
        // 2. Single-line comments.
        // 3. Quotes - double or single.
        
        while (index < content.length()) {
            char c = content.charAt(index++);
            
            // Handle comments.
            if (c == '/') {
                output.append(c);
                
                if (index >= content.length()) {
                    break;
                }
                
                c = content.charAt(index++);
                output.append(c);
                
                if (c == '*') {
                    // Find comment terminator.
                    while (index < content.length()) {
                        c = content.charAt(index++);
                        output.append(c);
                        
                        if (c == '*') {
                            if (index >= content.length()) {
                                break;
                            }
                            c = content.charAt(index++);
                            output.append(c);
                            
                            if (c == '/') {
                                break;
                            }
                        }
                    }
                }
                else if (c == '/') {
                    // Find end of line.
                    while (index < content.length()) {
                        c = content.charAt(index++);
                        output.append(c);

                        if (c == '\r' || c == '\n') {
                            break;
                        }
                    }
                }
            }
            
            // Handle strings.
            if (c == '"' || c == '\'') {
                output.append(targetChar);
                
                while (index < content.length()) {
                    char d = content.charAt(index++);
                    
                    if (d == '\\') {
                        if (index >= content.length()) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        d = content.charAt(index++);
                        
                        if ((targetChar == '"' && d == '\'') || (targetChar == '\'' && d == '"')) {
                            output.append(d);
                        }
                        else {
                            output.append('\\').append(d);
                        }
                    }
                    else if (d == c) {
                        // End of string.
                        output.append(targetChar);
                        break;
                    }
                    else if (d == targetChar) {
                        output.append('\\').append(d);
                    }
                    else {
                        output.append(d);
                    }
                }
                
                continue;
            }
            
            // Anything else, just append.
            output.append(c);
        }
        
        return output.toString();
    }
    
    public static void test(String s, char targetChar, String expect) {
        String result = replaceQuotes(s, targetChar);
        
        System.out.println(s + ", " + targetChar + " => " + result);
        
        if (!result.equals(expect)) {
            System.out.println("Error: expected " + expect);
        }
    }
    
    public static void main(String[] args) {
        test("s = 'aa';", '\'', "s = 'aa';");
        test("s = 'aa';", '"', "s = \"aa\";");

        test("s = 'a\\'a\"a';", '\'', "s = 'a\\'a\"a';");
        test("s = 'a\\'a\"a';", '"', "s = \"a'a\\\"a\";");
    }
}
