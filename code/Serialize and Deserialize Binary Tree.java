/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    private static void serialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("N").append(",");
        }
        else {
            sb.append(node.val).append(",");
            serialize(node.left, sb);
            serialize(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] a = data.split(",");
        int[] i = { 0 };
        return deserialize(a, i);
    }
    
    private static TreeNode deserialize(String[] a, int[] i) {
        int j = i[0]++;
        
        if (a[j].equals("N")) {
            return null;
        }
        
        TreeNode node = new TreeNode(Integer.parseInt(a[j]));
        node.left = deserialize(a, i);
        node.right = deserialize(a, i);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
