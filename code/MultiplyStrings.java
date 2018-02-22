class Solution {
    public String multiply(String num1, String num2) {
        // The product cannot exceed the lengths of num1 + num2.
        int[] answer = new int[num1.length() + num2.length()];

        // Strings have the most significant digit first, but answer is reversed.
        for (int i = num1.length() - 1; i >= 0; i--) {
            int start1 = num1.length() - i - 1;
            
            for (int j = num2.length() - 1; j >= 0; j--) {
                int start2 = num2.length() - j - 1;
                
                answer[start1 + start2] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }

        // Perform carries all at once to save time.
        for (int i = 0; i < answer.length - 1; i++) {
            if (answer[i] > 9) {
                answer[i + 1] += answer[i] / 10;
                answer[i] %= 10;
            }
        }

        // Find last non-zero digit.
        int lastNonZeroIndex = 0;
        for (int i = answer.length - 1; i >= 0; i--) {
            if (answer[i] != 0) {
                lastNonZeroIndex = i;
                break;
            }
        }

        // Form answer string in reverse order.
        StringBuilder sb = new StringBuilder(lastNonZeroIndex + 1);
        for (int i = lastNonZeroIndex; i >= 0; i--) {
            sb.append(answer[i]);
        }
        
        return sb.toString();
    }
}
