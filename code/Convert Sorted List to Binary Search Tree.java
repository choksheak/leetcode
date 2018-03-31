/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        // Start from before head so that it will end up right before the middle node.
        ListNode slow = new ListNode(0);
        slow.next = head;
        ListNode fast = head;
        
        // Find the middle node.
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        // Make the nodes more readable.
        ListNode middle = slow.next;
        ListNode right = middle.next;
        
        // Terminate the left list.
        slow.next = null;
        
        // Return tree.
        TreeNode tree = new TreeNode(middle.val);
        tree.left = (head == middle) ? null : sortedListToBST(head);
        tree.right = sortedListToBST(right);
        
        return tree;
    }
}
