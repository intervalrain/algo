package src.Topic.TreesAndGraphs;

import src.DioUtility.DioInt.TreeNode;

/**
 * T1 and T2 are very large binary trees, with T1 much bigger than T2.
 * Create an algorithm to determine if T2 is a subtree of T1.
 * A tree T2 is a subtree if there exists a node n in T1 such that the subtree of n is identical to T2.
 * That is, if you cut off the tree at node n, the two trees would be identical.
 */

public class CheckSubtree {
    // sol1
    public boolean containsTree(TreeNode t1, TreeNode t2){
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();
        
        getOrderString(t1, string1);
        getOrderString(t2, string2);

        return string1.indexOf(string2.toString()) != -1;
    }

    public void getOrderString(TreeNode node, StringBuilder sb){
        if (node == null){
            sb.append("X ");             // add null indicator
            return;
        }
        sb.append(node.val + " ");      // add root
        getOrderString(node.left, sb);  // add left
        getOrderString(node.right, sb); // add right
    }

    public static void main(String[] args){
        TreeNode root = TreeNode.buildTree(new Integer[]{1,2,3,4});
        CheckSubtree clz = new CheckSubtree();
        System.out.println(clz.containsTree(root, root.right));
    }

}
