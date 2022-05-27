package src.Topic.TreesAndGraphs;

import src.DioUtility.DioInt.TreeNode;

/**
 * Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a binary search tree.
 * You may assume that each node has a link to its parent.
 */

public class successor {

    TreeNode inorderSucc(TreeNode n){
        if (n == null) return null;

        if (n.right != null){
            return leftMostChild(n.right);
        } else {
            TreeNode q = n;
            TreeNode x = q.parent;
            while (x != null && x.left != q){
                q = x;
                x = x.parent;
            }
            return x;
        }
    }

    TreeNode leftMostChild(TreeNode n){
        if (n == null)
            return null;
        while (n.left != null)
            n = n.left;
        return n;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        TreeNode child = root.getChildAt(6);
        successor clz = new successor();
        System.out.println(clz.inorderSucc(child));
    }
}
