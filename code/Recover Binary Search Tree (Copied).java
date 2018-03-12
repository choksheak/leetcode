/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    private TreeNode node1;
    private TreeNode node2;
    private TreeNode prev;
    
    public void recoverTree(TreeNode root) {
        traverse(root);
        
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }
    
    private void traverse(TreeNode root) {
        if (root == null)
            return;
            
        traverse(root.left);
        
        if (prev != null && prev.val >= root.val) {
            if (node1 == null) {
                node1 = prev;
            }
            
            node2 = root;
        }
        
        prev = root;

        traverse(root.right);
    }
}
