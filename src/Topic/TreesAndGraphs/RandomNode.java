package src.Topic.TreesAndGraphs;

import java.util.Random;

import src.DioUtility.DioInt.TreeNode;

public class RandomNode extends TreeNode{

    int size;

    public RandomNode() {
    }

    public RandomNode(int val) {
        super(val);
        this.size = 1;
    }

    public RandomNode(int val, RandomNode left, RandomNode right) {
        super(val, left, right);
        this.size = 1 + left.size + right.size;
    }

    public RandomNode(int a, int b) {
        super(a, b);
        this.size = 2;
    }

    public RandomNode(int... array) {
        super(array);
        this.size = array.length;
    }

    public TreeNode getRandomNode(){
        int leftSize = left == null ? 0 : left.size();
        Random random = new Random();
        int index = random.nextInt(this.size());
        RandomNode left = (RandomNode)(this.left);
        RandomNode right = (RandomNode)(this.left);
        if (index < leftSize){
            return left.getRandomNode();
        } else if (index == leftSize){
            return this;
        } else {
            return right.getRandomNode();
        }
    }

    public void insertInOrder(int val){
        RandomNode left = (RandomNode) this.left;
        RandomNode right = (RandomNode) this.right;
        if (val <= this.val){
            if (left == null){
                left = new RandomNode(val);
            } else {
                left.insertInOrder(val);
            }
        } else {
            if (right == null){
                right = new RandomNode(val);
            } else {
                right.insertInOrder(val);
            }
        }
        this.size++;
    }

    public RandomNode find(int target){
        if (target == this.val){
            return this;
        } else if (target <= this.val){
            RandomNode left = (RandomNode) this.left;
            return left != null ? left.find(target) : null;
        } else if (target > this.val){
            RandomNode right = (RandomNode) this.right;
            return right != null ? right.find(target) : null;
        }
        return null;
    }
}
