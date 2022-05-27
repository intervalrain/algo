package src.Topic.TreesAndGraphs;

import src.DioUtility.DioInt.TreeNode;

/**
 * Minimal Tree
 * 
 * Given a sorted (increasing order) array with unique integer elements,
 * write an algorithm to create binary search tree with minimal height.
 */

public class MinimalTree {
    TreeNode createMinimalBST(int... array){
        return createMinimalBST(array, 0, array.length-1);
    }
    TreeNode createMinimalBST(int[] array, int start, int end){
        if (start > end) return null;
        int mid = start + (end - start)/2;
        TreeNode node = new TreeNode(array[mid]);
        node.left = createMinimalBST(array, start, mid-1);
        node.right = createMinimalBST(array, mid+1, end);
        return node;
    }

    public void traverse(TreeNode root){
        if (root == null) return;
        traverse(root.left);
        System.out.println(root.val + " ");
        traverse(root.right);
    }

    public static void main(String[] args){
        MinimalTree clz = new MinimalTree();
        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        TreeNode root = clz.createMinimalBST(array);
        clz.traverse(root);
    }

}
