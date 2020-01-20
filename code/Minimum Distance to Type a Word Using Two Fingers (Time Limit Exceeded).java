/*
You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate, for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).

Given the string word, return the minimum total distance to type such string using only two fingers. The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|. 

Note that the initial positions of your two fingers are considered free so don't count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.

 

Example 1:

Input: word = "CAKE"
Output: 3
Explanation: 
Using two fingers, one optimal way to type "CAKE" is: 
Finger 1 on letter 'C' -> cost = 0 
Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2 
Finger 2 on letter 'K' -> cost = 0 
Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1 
Total distance = 3

Example 2:

Input: word = "HAPPY"
Output: 6
Explanation: 
Using two fingers, one optimal way to type "HAPPY" is:
Finger 1 on letter 'H' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
Finger 2 on letter 'P' -> cost = 0
Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
Total distance = 6

Example 3:

Input: word = "NEW"
Output: 3

Example 4:

Input: word = "YEAR"
Output: 7

 

Constraints:

    2 <= word.length <= 300
    Each word[i] is an English uppercase letter.
*/

class Solution {
    private static class Point {
        final int x;
        final int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
    
    public int minimumDistance(String word) {
        Point freePosition = new Point(-1, -1);
        return minimumDistance(word, 0, freePosition, freePosition);
    }
    
    private static int minimumDistance(String word, int startIndex, Point finger1, Point finger2) {
        if (startIndex == word.length()) {
            return 0;
        }
        
        char c = word.charAt(startIndex);
        Point charPosition = getCharPosition(c);
        startIndex++;
        
        // Move finger1 to charPosition.
        int distance1 = getDistance(finger1, charPosition)
                + minimumDistance(word, startIndex, charPosition, finger2);
        
        // Move finger2 to charPosition.
        int distance2 = getDistance(finger2, charPosition)
                + minimumDistance(word, startIndex, finger1, charPosition);
        
        return Math.min(distance1, distance2);
    }
    
    private static Point getCharPosition(char c) {
        int offset = c - 'A';
        return new Point(offset % 6, offset / 6);
    }
    
    private static int getDistance(Point p1, Point p2) {
        // When the finger is free, the distance is always zero.
        if (p1.x == -1 || p2.x == -1) {
            return 0;
        }
        
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
