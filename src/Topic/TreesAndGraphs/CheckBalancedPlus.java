package src.Topic.TreesAndGraphs;

import src.DioUtility.DioInt.TreeNode;

/**
 * In the previous version, we recurse each ndoe through its entire subtree.
 * This means getHeight is called repeatedly on the same nodes.
 * The algorithm is O(n log n) since each node is "touched" once per node above it.
 * 
 * We may improve by checking if the tree is balanced at the same time as it's checking heights.
 */
public class CheckBalancedPlus {
    int checkHeight(TreeNode root){
        if (root == null) return -1;
        
        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1){
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }

    }
    boolean isBalanced(TreeNode root){
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    public static void main(String[] args){
        TreeNode root1 = new TreeNode(1,2,3,4,5,6,7,8,9,10,11);  // true
        TreeNode root2 = TreeNode.arrayToTree(new Integer[]{1,2,3,4,null,null,null,5,null}); // false

        CheckBalancedPlus clz = new CheckBalancedPlus();
        System.out.println(clz.isBalanced(root1));
        System.out.println(clz.isBalanced(root2));
    }
}
