/*
https://practice.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1

Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function would be added by GfG's Online Judge.*/


/* A Binary Tree node
class Node
{
    int data;
    Node left, right;
    Node(int item)
    {
        data = item;
        left = right = null;
    }
}*/
class GfG
{
	void printBoundary(Node node)
	{
	    if (node == null) return;
	    System.out.print(node.data + " ");
	    
		printLeft(node.left);
		printLeaves(node.left);
		printLeaves(node.right);
		printRight(node.right);
	}
	
	private static void printLeft(Node node) {
	    if (node == null) return;
	    if (node.left == null && node.right == null) return;
	    
	    System.out.print(node.data + " ");
	    
	    if (node.left != null) {
	        printLeft(node.left);
	    }
	    else {
	        printLeft(node.right);
	    }
	}
	
	private static void printLeaves(Node node) {
	    if (node == null) return;
	    
	    if (node.left == null && node.right == null) {
	        System.out.print(node.data + " ");
	    }
	    else {
    	    printLeaves(node.left);
	        printLeaves(node.right);
	    }
	}
	
	private static void printRight(Node node) {
	    if (node == null) return;
	    if (node.left == null && node.right == null) return;
	    
	    if (node.right != null) {
	        printRight(node.right);
	    }
	    else {
	        printRight(node.left);
	    }

        // Print from bottom-up.
	    System.out.print(node.data + " ");
	}
}
