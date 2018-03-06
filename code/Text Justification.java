class Solution {
    private List<String> answer = new ArrayList<>();
    private List<String> currentLine = new ArrayList<>();
    private StringBuilder sb = new StringBuilder();
    private int currentLength = 0;
    private int maxWidth;
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        this.maxWidth = maxWidth;
        
        for (String word: words) {
            if (word.length() == 0) {
                continue;
            }
            
            int newLength = (currentLength > 0) ? (currentLength + 1 + word.length()) : word.length();
            
            if (newLength > maxWidth) {
                addAnswer(false);
                currentLength = word.length();
                currentLine.clear();
            }
            else {
                currentLength = newLength;
            }

            currentLine.add(word);
        }
        
        addAnswer(true);
        
        if (answer.isEmpty()) {
            for (int i = 0; i < maxWidth; i++) {
                sb.append(' ');
            }
            answer.add(sb.toString());
        }
        
        return answer;
    }
    
    private void addAnswer(boolean isLastLine) {
        if (currentLength == 0) {
            return;
        }
        
        int extraSpaces = maxWidth - currentLength;
        sb.setLength(0);
        sb.append(currentLine.get(0));
        
        int numGaps = currentLine.size() - 1;
        int extraSpacesPerGap = (!isLastLine && numGaps > 0) ? (extraSpaces / numGaps) : 0;
        int extraSpaceForFirstNGaps = (!isLastLine && numGaps > 0) ? (extraSpaces - (extraSpacesPerGap * numGaps)) : 0;

        for (int i = 1; i < currentLine.size(); i++) {
            int numSpaces = 1 + extraSpacesPerGap;
            
            if (i <= extraSpaceForFirstNGaps) {
                numSpaces++;
            }

            for (int j = 0; j < numSpaces; j++) {
                sb.append(' ');
            }
            
            sb.append(currentLine.get(i));
        }
        
        while (sb.length() < maxWidth) {
            sb.append(' ');
        }

        answer.add(sb.toString());
    }
}
