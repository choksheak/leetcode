/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        
        ListNode n1 = preHead;
        ListNode n2 = head;
        
        for (int i = 0; i < n; i++) {
            n2 = n2.next;
        }
        
        while (n2 != null) {
            n1 = n1.next;
            n2 = n2.next;
        }
        
        n1.next = n1.next.next;
        return preHead.next;
    }
}
