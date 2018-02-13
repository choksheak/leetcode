/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode prev = preHead;
        ListNode[] a = new ListNode[k];
        
        while (true) {
            int i = 0;
            ListNode curr = prev.next;
            
            while (curr != null && i < k) {
                a[i++] = curr;
                curr = curr.next;                
            }
            
            if (i != k) {
                break;
            }

            while (--i >= 0) {
                prev = prev.next = a[i];
            }
            
            prev.next = curr;
        }
        
        return preHead.next;
    }
}
