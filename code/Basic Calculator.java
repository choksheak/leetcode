class Solution {
    private class Level {
        public int num;
        public char sign;
        
        public Level(int num, char sign) {
            this.num = num;
            this.sign = sign;
        }
        
        public void eatNum(int num) {
            if (sign == '+') {
                this.num += num;
            }
            else {
                this.num -= num;
            }
        }
    }
    
    public int calculate(String s) {
        List<Level> levels = new ArrayList<>();
        Level currLevel = new Level(0, '+');
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                levels.add(currLevel);
                currLevel = new Level(0, '+');
            }
            else if (c == ')') {
                int num = currLevel.num;
                currLevel = levels.remove(levels.size() - 1);
                currLevel.eatNum(num);
            }
            else if (c == '+' || c == '-') {
                currLevel.sign = c;
            }
            else if (c >= '0' && c <= '9') {
                int num = c - '0';
                int j = i + 1;
                
                while (j < s.length()) {
                    char digit = s.charAt(j);
                    if (digit < '0' || digit > '9') {
                        break;
                    }
                    num = (num * 10) + (digit - '0');
                    j++;
                }
                
                currLevel.eatNum(num);
                
                i = j - 1;
            }
        }
        
        return currLevel.num;
    }
}
