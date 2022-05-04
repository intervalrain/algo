package src.Topic.StackAndQueues;

import src.DioUtility.Generic.Stack;

/**
 * How would you design a stack which, in addition to push and pop,
 * has a function min which returns the minimum element?
 * 
 * Push, pop and min should all operate in O(1) time.
 */

public class StackMin extends Stack<Node>{
    public void push(int val){
        int newMin = Math.min(val, min());
        super.push(new Node(val, newMin));
    }

    public int min(){
        if (this.isEmpty()){
            return Integer.MAX_VALUE;
        } else {
            return peek().min;
        }
    }

    public static void main(String[] args){
        StackMin stack = new StackMin();
        stack.push(5);
        stack.push(6);
        stack.push(3);
        stack.push(7);
        stack.pop();
        stack.pop();
    }

}

class Node{
    public int value;
    public int min;
    public Node(int v, int m){
        this.value = v;
        this.min = m;
    }
}
