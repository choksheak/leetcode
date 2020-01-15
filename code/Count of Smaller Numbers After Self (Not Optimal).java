/*
You are given an integer array nums and you have to return a new counts array.
The counts array has the property where counts[i] is the number of smaller
elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
*/

/*
Note: The optimal solution requires using mergesort and then recording the result.
The solution below is non-optimal O(N^2), but is an ok brute force solution.
*/

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<Integer>();
        }

        List<Integer> answer = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            answer.add(0);
        }
        
        for (int i = nums.length - 2; i >= 0; i--) {
            int count = 0;
            
            for (int j = i + 1; j < nums.length; j++) {
                // Optimize by making use of already computed result.
                if (nums[i] == nums[j]) {
                    count += answer.get(j);
                    break;
                }
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
            
            answer.set(i, count);
        }
        
        return answer;
    }
}
