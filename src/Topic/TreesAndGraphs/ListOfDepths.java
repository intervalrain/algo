package src.Topic.TreesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;

import src.DioUtility.DioInt.TreeNode;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all the ndoes at each depth
 * (e.g. if you have a tree with depth D, you'll have D linked list.)
 */

public class ListOfDepths {
    ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root){
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }
    void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level){
        if (root == null) return;
        LinkedList<TreeNode> list = null;
        if (lists.size() == level){
            list = new LinkedList<>();
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedList(root.left, lists, level+1);
        createLevelLinkedList(root.right, lists, level+1);
    }

    ArrayList<LinkedList<TreeNode>> createLevelLinkedList2(TreeNode root){
        ArrayList<LinkedList<TreeNode>> nodeList = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        nodeList.add(new LinkedList<>(list));
        createLevelLinkedList(nodeList, list);
        return nodeList;
    }
    void createLevelLinkedList(ArrayList<LinkedList<TreeNode>> nodeList, LinkedList<TreeNode> list){
        if (list.size() == 0) return;
        LinkedList<TreeNode> curr = list;
        LinkedList<TreeNode> next = new LinkedList<>();
        while (curr.size() != 0){
            TreeNode node = curr.peek();
            if (node.left != null) next.add(node.left);
            if (node.right != null) next.add(node.right);
            curr.poll();
        }
        if (next.size() > 0) nodeList.add(new LinkedList<>(next));
        createLevelLinkedList(nodeList, next);
    }

    public static void main(String[] args){
        ListOfDepths clz = new ListOfDepths();
        TreeNode root = new MinimalTree().createMinimalBST(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15});
        ArrayList<LinkedList<TreeNode>> nodeList = clz.createLevelLinkedList(root);
        for (int i = 0; i < nodeList.size(); i++){
            LinkedList<TreeNode> node = nodeList.get(i);
            while (node.size() > 0){
                System.out.print(node.poll().val + " ");
            }
            System.out.println();
        }
    }
}
