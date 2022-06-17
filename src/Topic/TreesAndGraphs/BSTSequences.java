package src.Topic.TreesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;

import src.DioUtility.DioInt.TreeNode;

/**
 * BST Sequences
 * 
 * A binary search tree was created by traversing through an array form left to right and inserting each element.
 * Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree. 
 */ 

public class BSTSequences {
    ArrayList<LinkedList<Integer>> allSequences(TreeNode node){
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

        if (node == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(node.val);

        // recurse on left and right subtrees
        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);
        
        // weave together each list from left and right sides
        for (LinkedList<Integer> left : leftSeq){
            for (LinkedList<Integer> right : rightSeq){
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, 
                    ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix){
        // One list is empty. Add remainder to a cloned prefix and store result
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        /* Recurse with head of first added to the prefix. Removing the head will damage first, 
           so we'll need to put it back where we found it afterwards. */
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        /* Do the same thing with second, damaging and then restoring the list */
        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }

    public static void main(String[] args){
        TreeNode root = TreeNode.arrayToTree(new Integer[]{1,2,3,4});
        BSTSequences clz = new BSTSequences();
        ArrayList<LinkedList<Integer>> e = clz.allSequences(root);
        for (LinkedList<Integer> l : e){
            System.out.print("[");
            StringBuilder sb = new StringBuilder();
            for (Integer i : l){
                sb.append(i);
                sb.append(",");
            }
            System.out.print(sb.substring(0, sb.length()-1));
            System.out.print("]");
            System.out.println();
        }
    }
}

