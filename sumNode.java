import DioUtility.TreeNode;

/**
 * To illustrate big(O) of sumNode:
 * Although it's a binary search tree doesn't mean that its big(O) is O(nlog(n)).
 * n = 1, count = 3;
 * n = 5, count = 11;
 * n = 10, count = 21;
 * n = 15, count = 31;
 * count = 2 * n + 1;
 * so, big(O) = O(n);
 */

public class sumNode {
    static int count;
    public sumNode(){
        count = 0;
    }
    int sum(TreeNode<Integer> node){
        count++;
        if (node == null || node.val == null)
            return 0;
        return sum(node.left) + node.val + sum(node.right);
    }

    public static void main(String[] args){
        TreeNode<Integer> root = new TreeNode<>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
        sumNode clz = new sumNode();
        System.out.println(clz.sum(root));
        System.out.println(count);
    }
}
