class Solution {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        
        StringBuilder sb = new StringBuilder();
        numberToWords(sb, num);
        return sb.toString();
    }
    
    private static void numberToWords(StringBuilder sb, int num) {
        if (num >= 1000000000) {
            numberToWords(sb, num / 1000000000);
            sb.append(" Billion");
            num %= 1000000000;
        }
        
        if (num >= 1000000) {
            numberToWords(sb, num / 1000000);
            sb.append(" Million");
            num %= 1000000;
        }
        
        if (num >= 1000) {
            numberToWords(sb, num / 1000);
            sb.append(" Thousand");
            num %= 1000;
        }
        
        if (num >= 100) {
            numberToWords(sb, num / 100);
            sb.append(" Hundred");
            num %= 100;
        }
        
        if (num >= 20) {
            if (sb.length() > 0) sb.append(' ');
            
            switch (num / 10) {
                case 2: sb.append("Twenty"); break;
                case 3: sb.append("Thirty"); break;
                case 4: sb.append("Forty"); break;
                case 5: sb.append("Fifty"); break;
                case 6: sb.append("Sixty"); break;
                case 7: sb.append("Seventy"); break;
                case 8: sb.append("Eighty"); break;
                case 9: sb.append("Ninety"); break;
            }
            num %= 10;
        }
        
        if (num > 0 && sb.length() > 0) sb.append(' ');
        
        switch (num) {
            case 1: sb.append("One"); break;
            case 2: sb.append("Two"); break;
            case 3: sb.append("Three"); break;
            case 4: sb.append("Four"); break;
            case 5: sb.append("Five"); break;
            case 6: sb.append("Six"); break;
            case 7: sb.append("Seven"); break;
            case 8: sb.append("Eight"); break;
            case 9: sb.append("Nine"); break;
            case 10: sb.append("Ten"); break;
            case 11: sb.append("Eleven"); break;
            case 12: sb.append("Twelve"); break;
            case 13: sb.append("Thirteen"); break;
            case 14: sb.append("Fourteen"); break;
            case 15: sb.append("Fifteen"); break;
            case 16: sb.append("Sixteen"); break;
            case 17: sb.append("Seventeen"); break;
            case 18: sb.append("Eighteen"); break;
            case 19: sb.append("Nineteen"); break;
        }
    }
}
