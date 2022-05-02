package src.DioUtility.Topic.Sorting;

import static org.junit.Assert.assertArrayEquals;

import java.util.LinkedList;

import org.junit.Test;

import src.DioUtility.DioInt.arrayGenerator;
import src.DioUtility.Generic.TreeNode;

/**
 * Tree Sort: 
 * 
 * Tree sort is a sorting algorithm based on Binary Search Tree(BST) data structure.
 * It first creates a binary search tree from the elements of the input list or array,
 * and then performs an in-order traversal on the created BST to get the elements in sorted order.
 * 
 * Best Time Complexity: O(nlogn)
 * Avg Time Complexity: O(nlogn)
 * Worst Time Complexity: O(n^2): All nodes are at the same side -> linked list.
 * Space Complexity: O(n)
 * 
 */

public class TreeSort {

    // smaller go to left, equal or bigger go to right
    private static TreeNode<Integer> insert(TreeNode<Integer> root, Integer val){
        if (root == null){
            root = new TreeNode<>(val);
            return root;
        }
        if (val < root.val){
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    public static void sort(int[] array){
        TreeNode<Integer> root = new TreeNode<>(array[0]);
        for (int i = 1; i < array.length; i++){
            insert(root, array[i]);
        }
        
        LinkedList<Integer> res = new LinkedList<>();
        traverse(root, res);
        int i = 0;
        while (!res.isEmpty()){
            array[i++] = res.pop();
        }
    }

    private static void traverse(TreeNode<Integer> root, LinkedList<Integer> list){
        if (root == null)
            return;
        traverse(root.left, list);
        list.add(root.val);
        traverse(root.right, list);
    }
    
    @Test
    public void test1(){
        int[] array = new int[]{3,5,7,8,4,2,1,9,6};
        sort(array);
        int[] expected = new int[]{1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expected, array);
    }

    @Test
    public void test2(){
        int[] array = new int[]{9,8,7,6,5,4,3,2,1};
        sort(array);
        int[] expected = new int[]{1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expected, array);
    }

    @Test
    public void randomTest(){
        int[] array    = new arrayGenerator(128, false).toArray();
        sort(array);
        int[] expected = new arrayGenerator(128, true).toArray();
        assertArrayEquals(expected, array);
    }

}
