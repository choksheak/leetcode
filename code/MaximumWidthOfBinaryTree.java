class Solution {
    private static class NodeBox {
        private static final NodeBox NULL = new NodeBox(null);
        private TreeNode node;
        
        private NodeBox(TreeNode node) {
            this.node = node;
        }
        
        private static NodeBox Create(TreeNode node) {
            return node != null ? new NodeBox(node) : NULL;
        }
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int max = 1;
        LinkedList<NodeBox> a = new LinkedList<>();
        LinkedList<NodeBox> b = new LinkedList<>();
        a.addLast(new NodeBox(root));
        
        do {
            do {
                NodeBox n = a.removeFirst();
                
                if (n.node == null) {
                    if (!b.isEmpty()) {
                        b.add(NodeBox.NULL);
                        b.add(NodeBox.NULL);
                    }
                }
                else {
                    if (n.node.left != null || !b.isEmpty()) {
                        b.add(NodeBox.Create(n.node.left));
                    }
                    if (n.node.right != null || !b.isEmpty()) {
                        b.add(NodeBox.Create(n.node.right));
                    }
                }
            }
            while (!a.isEmpty());
            
            // Trim trailing null NodeBoxes from b.
            while (!b.isEmpty() && b.getLast() == NodeBox.NULL) {
                b.removeLast();
            }
            
            // b.size() is the current width.
            max = Math.max(max, b.size());
            
            // Swap a and b.
            LinkedList<NodeBox> c = a;
            a = b;
            b = c;
        }
        while (!a.isEmpty());
        
        return max;
    }
}
