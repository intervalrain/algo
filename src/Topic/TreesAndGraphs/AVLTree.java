package src.Topic.TreesAndGraphs;

import java.util.Arrays;

import src.DioUtility.DioInt.TreeNode;

/**
 * An AVL tree stores in each node the height of the subtrees rooted at this node. 
 * Then, for any node, we can check if it is height balanced: 
 * that the height of the left subtree and the height of the right subtree differ by no more than one.
 * This prevents situations where the tree gets too lopsided.
 * 
 *    balance(n) = n.left.height - n.right.height
 *          -1 <= balance(n) <= 1
 */

public class AVLTree extends TreeNode{

    int height;
    AVLTree left;
    AVLTree right;
    AVLTree parent;

    public AVLTree() { 
        this.height = 1; 
    }

    public AVLTree(int val) {
        this.val = val;
        this.height = 1;
    }

    public AVLTree(int val, AVLTree left, AVLTree right) {
        this.val = val;
        this.left = left;
        this.right = right;
        if (left != null) this.left.parent = this;
        if (right != null) this.right.parent = this;
        this.height = 1 + Math.max(left.height, right.height);
    }

    public AVLTree(int a, int b) {
        this.val = a;
        this.left = new AVLTree(b);
        this.left.parent = this;
        this.height = 2;
    }

    // public AVLTree(int... array) {
    //     for (int i = 0; i < array.length; i++){
    //         insert(array[i]);
    //     }
    // }

    private int getHeight(){
        return getHeight(this);
    }
    private int getHeight(TreeNode node){
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    /**
     * When you insert a node, the balance of some nodes migh change to -2 or 2.
     * Therefore, when we "unwind" the recursive stack, we check and fix the balance at each node.
     * We do this through a series of rotations.
     * 
     * Rotations can be either left or right rotations. The right rotation is an inverse of the left rotation.
     */
    
    



    /**
     * Methods from TreeNode
     */
    private static AVLTree arrayToTree(int[] array, int index){
        if (index < array.length)
            return new AVLTree(array[index], arrayToTree(array, index*2+1), arrayToTree(array, index*2+2));;
        return null;
    }

    public static AVLTree arrayToTree(int[] array){
        return arrayToTree(array, 0);
    }

    private static AVLTree arrayToTree(Integer[] array, int index) {
        if (index >= array.length || array[index] == null)
            return null;
        if (index < array.length)
            return new AVLTree(array[index], arrayToTree(array, index*2+1), arrayToTree(array, index*2+2));;
        return null;
    }
    public static AVLTree arrayToTree(Integer[] array){
        return arrayToTree(array, 0);
    }
    public static AVLTree arrayToTreeInRightSubtreeOnly(Integer[] array){
        AVLTree curr = new AVLTree(array[array.length-1]);
        for (int i = array.length-2; i >= 0; i--){
            curr = new AVLTree(array[i], null, curr);
        }
        return curr;
    }
    public static int size(AVLTree root){
        if (root == null) return 0;
        return sizeSub(root, 0) + 1;
    }
    public int size(){
        int[] cnt = new int[1];
        traverse(this, cnt);
        return cnt[0];
    }
    public void traverse(AVLTree root, int[] cnt){
        if (root == null) return;
        traverse(root.left, cnt);
        cnt[0]++;
        traverse(root.right, cnt);
    }

    private static int sizeSub(AVLTree root, int curr){
        if (root.left == null && root.right == null)
            return curr;
        else if (root.left == null && root.right != null)
            return sizeSub(root.right, curr * 2 + 2);
        else if (root.left != null && root.right == null)
            return sizeSub(root.left, curr * 2 + 1);
        return Math.max(sizeSub(root.left, curr * 2 + 1), sizeSub(root.right, curr * 2 + 2));
    }
    private static Integer[] treeToArray(AVLTree root){
        if (root == null) return null;
        Integer[] array = new Integer[AVLTree.size(root)];
        treeToArraySub(root, 0, array);
        return array;
    }

    private static void treeToArraySub(AVLTree root, int index, Integer[] array){
        if (root == null) return;
        array[index] = root.val;
        treeToArraySub(root.left, index * 2 + 1, array);
        treeToArraySub(root.right, index * 2 + 2, array);
    }

    public AVLTree getChildAt(int childIndex){
        return getChildAt(this, childIndex);
    }
    public AVLTree getChildAt(AVLTree root, int childIndex){
        if (childIndex == 0) return root;
        if (childIndex == 1) return root.left;
        if (childIndex == 2) return root.right;
        int mom = ((childIndex - 1) >> 1);
        int leaf = childIndex - (mom << 1);
        AVLTree tmp = getChildAt(root, mom);
        return getChildAt(tmp, leaf);
    }

    public static boolean equals(AVLTree a, AVLTree b){
        return Arrays.toString(treeToArray(a)).equals(Arrays.toString(treeToArray(b)));
    }

    public String toString(){
        return Arrays.toString(AVLTree.treeToArray(this));
    }
    
    public static void main(String[] args){
        AVLTree root = new AVLTree();
        System.out.println(root.toString());
        System.out.println(root.getHeight());
    }
}
