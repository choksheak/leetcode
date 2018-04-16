/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        
        // Count the list size.
        int size = 0;
        for (ListNode node = head; node != null; node = node.next) {
            size++;
        }
        
        // Make sure k doesn't exceed list size.
        k %= size;
        
        if (k == 0) {
            return head;
        }
        
        // Find the (k + 1)-th node counting from the end.
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        fast.next = head;
        head = slow.next;
        slow.next = null;
        
        return head;
    }
}
