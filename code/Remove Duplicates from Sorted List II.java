/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        
        ListNode curr = preHead;
        
        while (curr.next != null && curr.next.next != null) {
            if (curr.next.val == curr.next.next.val) {
                ListNode temp = curr.next.next.next;
                
                while (temp != null && temp.val == curr.next.val) {
                    temp = temp.next;
                }
                
                curr.next = temp;
            }
            else {
                curr = curr.next;
            }
        }
        
        return preHead.next;
    }
}
