/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode curNode = null;
        int sum = 0;
        
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            
            ListNode newNode = new ListNode(sum % 10);
            sum /= 10;
            
            if (head == null) {
                head = curNode = newNode;
            }
            else {
                curNode.next = newNode;
                curNode = newNode;
            }
        }
        
        if (sum != 0) {
            curNode.next = new ListNode(sum);
        }
        
        return head;
    }
}
