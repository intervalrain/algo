package src.Topic.TreesAndGraphs;

import src.DioUtility.DioInt.TreeNode;

/**
 * Validate BST: Implement a function to check if a binary tree is a binary search tree
 */

public class ValidateBST {
    int index = 0;
    void copyBST(TreeNode root, int[] array){
        if (root == null) return;
        copyBST(root.left, array);
        array[index] = root.val;
        index++;
        copyBST(root.right, array);
    }

    boolean checkBST(TreeNode root){
        int[] array = new int[root.size()];
        copyBST(root, array);
        for (int i = 1; i < array.length; i++){
            if (array[i] <= array[i-1]) return false;
        }
        return true;
    }

    boolean checkBST2(TreeNode root){
        return checkSub(root, new int[]{Integer.MIN_VALUE});
    }
    boolean checkSub(TreeNode root, int[] last){
        if (root == null) return true;
        if (!checkSub(root.left, last)) return false;
        if (last[0] != Integer.MIN_VALUE && root.val <= last[0]){
            return false;
        }
        last[0] = root.val;
        if (!checkSub(root.right, last)) return false;
        return true;
    }

    boolean checkBST3(TreeNode root){
        return checkHelper(root, null, null);
    }
    boolean checkHelper(TreeNode root, Integer min, Integer max){
        if (root == null) return true;
        if ((min != null && root.val < min) || (max != null && root.val > max)){
            return false;
        }
        if (!checkHelper(root.left, min, root.val) || !checkHelper(root.right, root.val, max)){
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        MinimalTree clz = new MinimalTree();
        TreeNode root = clz.createMinimalBST(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        ValidateBST cls = new ValidateBST();
        System.out.println(cls.checkBST(root));
        System.out.println(cls.checkBST2(root));
        System.out.println(cls.checkBST3(root));
    }
}
