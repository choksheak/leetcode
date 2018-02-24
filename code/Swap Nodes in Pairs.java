/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode preHead = new ListNode(0);
        ListNode prev = preHead;
        prev.next = head;
        
        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;
            ListNode c = b.next;
            
            prev.next = b;
            b.next = a;
            a.next = c;
            prev = a;
        }
        
        return preHead.next;
    }
}
