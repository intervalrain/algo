package src.Topic.TreesAndGraphs;

import src.DioUtility.DioInt.TreeNode;

/**
 * Implement a function to check if a binary tree is balanced.
 * For the purposes of this question,
 * a balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.
 */

public class CheckBalanced {
    int getHeight(TreeNode root){
        if (root == null) return -1;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
    boolean isBalanced(TreeNode root){
        if (root == null) return true;
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public static void main(String[] args){
        TreeNode root1 = new TreeNode(1,2,3,4,5,6,7,8,9,10,11);  // true
        TreeNode root2 = TreeNode.arrayToTree(new Integer[]{1,2,3,4,null,null,null,5,null}); // false

        CheckBalanced clz = new CheckBalanced();
        System.out.println(clz.isBalanced(root1));
        System.out.println(clz.isBalanced(root2));
    }
}
