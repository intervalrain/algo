package src.DioUtility.DioInt;

import java.util.Arrays;

public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
        if (left != null) this.left.parent = this;
        if (right != null) this.right.parent = this;
    }
    public TreeNode(int a, int b){
        this.val = a;
        this.left = new TreeNode(b);
        this.left.parent = this;
    }
    public TreeNode(int... array){
        TreeNode root = TreeNode.arrayToTree(array);
        this.val = root.val;
        this.left = root.left;
        this.right = root.right;
        this.left.parent = root;
        this.right.parent = root;
    }
    
    private static TreeNode arrayToTree(int[] array, int index){
        if (index < array.length)
            return new TreeNode(array[index], arrayToTree(array, index*2+1), arrayToTree(array, index*2+2));;
        return null;
    }

    public static TreeNode arrayToTree(int[] array){
        return arrayToTree(array, 0);
    }

    private static TreeNode arrayToTree(Integer[] array, int index) {
        if (index >= array.length || array[index] == null)
            return null;
        if (index < array.length)
            return new TreeNode(array[index], arrayToTree(array, index*2+1), arrayToTree(array, index*2+2));;
        return null;
    }
    public static TreeNode arrayToTree(Integer[] array){
        return arrayToTree(array, 0);
    }
    public static TreeNode arrayToTreeInRightSubtreeOnly(Integer[] array){
        TreeNode curr = new TreeNode(array[array.length-1]);
        for (int i = array.length-2; i >= 0; i--){
            curr = new TreeNode(array[i], null, curr);
        }
        return curr;
    }
    public static int size(TreeNode root){
        if (root == null) return 0;
        return sizeSub(root, 0) + 1;
    }
    public int size(){
        int[] cnt = new int[1];
        traverse(this, cnt);
        return cnt[0];
    }
    public void traverse(TreeNode root, int[] cnt){
        if (root == null) return;
        traverse(root.left, cnt);
        cnt[0]++;
        traverse(root.right, cnt);
    }

    private static int sizeSub(TreeNode root, int curr){
        if (root.left == null && root.right == null)
            return curr;
        else if (root.left == null && root.right != null)
            return sizeSub(root.right, curr * 2 + 2);
        else if (root.left != null && root.right == null)
            return sizeSub(root.left, curr * 2 + 1);
        return Math.max(sizeSub(root.left, curr * 2 + 1), sizeSub(root.right, curr * 2 + 2));
    }
    private static Integer[] treeToArray(TreeNode root){
        if (root == null) return null;
        Integer[] array = new Integer[TreeNode.size(root)];
        treeToArraySub(root, 0, array);
        return array;
    }

    private static void treeToArraySub(TreeNode root, int index, Integer[] array){
        if (root == null) return;
        array[index] = root.val;
        treeToArraySub(root.left, index * 2 + 1, array);
        treeToArraySub(root.right, index * 2 + 2, array);
    }

    public static TreeNode createMinimalBST(int... array){
        return createMinimalBST(array, 0, array.length-1);
    }
    private static TreeNode createMinimalBST(int[] array, int start, int end){
        if (start > end) return null;
        int mid = start + (end - start)/2;
        TreeNode node = new TreeNode(array[mid]);
        node.left = createMinimalBST(array, start, mid-1);
        node.right = createMinimalBST(array, mid+1, end);
        return node;
    }
    public TreeNode getChildAt(int childIndex){
        return getChildAt(this, childIndex);
    }
    public TreeNode getChildAt(TreeNode root, int childIndex){
        if (childIndex == 0) return root;
        if (childIndex == 1) return root.left;
        if (childIndex == 2) return root.right;
        int mom = ((childIndex - 1) >> 1);
        int leaf = childIndex - (mom << 1);
        TreeNode tmp = getChildAt(root, mom);
        return getChildAt(tmp, leaf);
    }

    public static boolean equals(TreeNode a, TreeNode b){
        return Arrays.toString(treeToArray(a)).equals(Arrays.toString(treeToArray(b)));
    }

    public String toString(){
        return Arrays.toString(TreeNode.treeToArray(this));
    }
}

