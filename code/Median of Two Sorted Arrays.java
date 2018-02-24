class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int pastMid = (len / 2) + 1;
        int secondLast = 0;
        int last = 0;
        int i = 0;
        int j = 0;
        
        while (true) {
            secondLast = last;
            
            if (i == nums1.length) {
                last = nums2[j++];
            }
            else if (j == nums2.length) {
                last = nums1[i++];
            }
            else if (nums1[i] <= nums2[j]) {
                last = nums1[i++];
            }
            else {
                last = nums2[j++];
            }
                             
            if (i + j == pastMid) {
                boolean isEven = (len % 2) == 0;
                return isEven ? ((secondLast + last) / 2.0) : last;
            }
        }
    }
}
