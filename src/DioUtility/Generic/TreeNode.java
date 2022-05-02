package src.DioUtility.Generic;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class TreeNode<T>{
    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    /** Constructor */
    public TreeNode(T val, TreeNode<T> left, TreeNode<T> right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public TreeNode(T val) {this(val, null, null);}
    public TreeNode() {}

    @SafeVarargs
    public TreeNode(T... array) {
        this(array[0]);
        TreeNode<T> root = new TreeNode<T>(array[0]);
        List<TreeNode<T>> q = new ArrayList<>();
        q.add(root);
        for (int i = 1; i < array.length; i++){
            TreeNode<T> node = new TreeNode<T>(array[i]);
            q.add(node);
        }
        for (int i = 0; i < array.length; i++){
            if (2 * i + 1 < array.length) q.get(i).left = q.get(2 * i + 1);
            if (2 * i + 2 < array.length) q.get(i).right = q.get(2 * i + 2);
        }
        if (array.length > 1) this.left = q.get(1);
        if (array.length > 2) this.right = q.get(2);
    }

    public TreeNode(List<T> array){
        this(array.get(0));
        TreeNode<T> root = new TreeNode<T>(array.get(0));
        List<TreeNode<T>> q = new ArrayList<>();
        q.add(root);
        for (int i = 0; i < array.size(); i++){
            TreeNode<T> node = new TreeNode<T>(array.get(i));
            q.add(node);
        }
        for (int i = 0; i < array.size(); i++){
            if (2 * i + 1 < array.size()) q.get(i).left = q.get(2 * i + 1);
            if (2 * i + 2 < array.size()) q.get(i).left = q.get(2 * i + 2);
        }
        if (array.size() > 1) this.left = q.get(1);
        if (array.size() > 2) this.right = q.get(2);
    }

    public int size(){
        return sizeSub(this, 0) + 1;
    }

    private int sizeSub(TreeNode<T> root, int curr){
        if (root.left == null && root.right == null)
            return curr;
        else if (root.left == null && root.right != null)
            return sizeSub(root.right, curr * 2 + 2);
        else if (root.left != null && root.right == null)
            return sizeSub(root.left, curr * 2 + 1);
        return Math.max(sizeSub(root.left, curr * 2 + 1), sizeSub(root.right, curr * 2 + 2));
    }

    public Object[] toArray(){
        TreeNode<T> root = this;
        Object[] array = new Object[this.size()];
        toArraySub(root, 0, array);
        return array;
    }

    private void toArraySub(TreeNode<T> root, int index, Object[] array){
        if (root == null) return;
        array[index] = root.val;
        toArraySub(root.left, index * 2 + 1, array);
        toArraySub(root.right, index * 2 + 2, array);
    }

    public String toString(){
        return Arrays.toString(this.toArray());
    }
    
}
