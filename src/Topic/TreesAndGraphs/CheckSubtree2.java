package src.Topic.TreesAndGraphs;

import src.DioUtility.DioInt.TreeNode;

public class CheckSubtree2 {
    boolean containsTree(TreeNode t1, TreeNode t2){
        if (t2 == null) return true;
        return subTree(t1, t2);
    }

    boolean subTree(TreeNode t1, TreeNode t2){
        if (t1 == null) {
            return false;
        } else if (t1.val == t2.val && matchTree(t1, t2)){
            return true;
        }
        return subTree(t1.left, t2) || subTree(t1.right, t2);
    }

    boolean matchTree(TreeNode t1, TreeNode t2){
        if (t1 == null && t2 == null){
            return true;
        } else if (t1 == null || t2 == null){
            return false;
        } else if (t1.val != t2.val){
            return false;
        } else {
            return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
        }
    }


    public static void main(String[] args){
        TreeNode root = TreeNode.buildTree(new Integer[]{1,2,3,4});
        CheckSubtree2 clz = new CheckSubtree2();
        System.out.println(clz.containsTree(root, root.right));
    }

}
