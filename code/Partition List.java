/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode leftHead = null;
        ListNode left = null;
        ListNode rightHead = null;
        ListNode right = null;
        ListNode curr = head;
        
        while (curr != null) {
            if (curr.val < x) {
                if (leftHead == null) {
                    leftHead = curr;
                }
                else {
                    left.next = curr;
                }
                left = curr;
            }
            else {
                if (rightHead == null) {
                    rightHead = curr;
                }
                else {
                    right.next = curr;
                }
                right = curr;
            }
            
            curr = curr.next;
        }
        
        if (left != null) {
            left.next = rightHead;
        }
        
        if (right != null) {
            right.next = null;
        }
        
        return (leftHead != null) ? leftHead : rightHead;
    }
}
