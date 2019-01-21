/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SerializeDeserializeBst {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    private static void serialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append('x');
            return;
        }

        sb.append(node.val);
        sb.append('/');
        serialize(node.left, sb);
        sb.append('\\');
        serialize(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        // Use a single-element int array as a simple mutable integer.
        return deserialize(data, new int[] { 0 });
    }
    
    private static TreeNode deserialize(String data, int[] index) {
        if (index[0] >= data.length()) {
            return null;
        }

        if (data.charAt(index[0]) == 'x') {
            index[0]++;
            return null;
        }
        
        TreeNode node = new TreeNode(0);

        while (index[0] < data.length()) {
            int c = data.charAt(index[0]++);
            if (c == '/') {
                node.left = deserialize(data, index);
                continue;
            }
            if (c == '\\') {
                node.right = deserialize(data, index);
                break;
            }
            node.val = (node.val * 10) + (c - '0');
        }

        return node;
    }

    public static void main(String[] args) {
      TreeNode node = new TreeNode(2);
      node.left = new TreeNode(1);
      node.right = new TreeNode(3);

      String s = serialize(node);
      TreeNode newNode = deserialize(s);

      System.out.println("root=" + newNode.val);
      System.out.println("left=" + newNode.left.val);
      System.out.println("right=" + newNode.right.val);
    }
}
