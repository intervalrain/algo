package src.Topic.TreesAndGraphs;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    static class MyStack{

        Queue<Integer> top;
        Queue<Integer> box;

        public MyStack(){
            top = new ArrayDeque<>();
            box = new ArrayDeque<>();
        }
        public void push(int x){
            top.add(x);
            alloc();
        }
        private void alloc(){
            while (top.size() > 1){
                box.add(top.poll());
            }
        }

        public int pop(){
            int tmp = top.poll();
            top = box;
            box = new ArrayDeque<>();
            alloc();
            return tmp;
        }

        public int top(){
            return top.peek();
        }

        public boolean empty(){
            return top.isEmpty() && box.isEmpty();
        }
    }
   

    public static void main(String[] args){
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.empty());

    }
}
