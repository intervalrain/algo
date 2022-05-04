package src.Topic.StackAndQueues;

import java.util.Stack;

/**
 * Sort Stack
 * 
 * Write a program to sort a stack such that the smallest items are on the top.
 * You can use an additional temporary stack,
 * but you may not copy the elements into any other data structure (such as array).
 * The stack supports the following operations: push, pop, peek, and isEmpty.
 */

public class SortStack {
    public static void sort(Stack<Integer> st){
        Stack<Integer> tmp = new Stack<>();
        while (!st.isEmpty()){
            int top = st.pop();
            while (!tmp.isEmpty() && tmp.peek() < top){
                st.push(tmp.pop());
            }
            tmp.push(top);
        }
        while (!tmp.isEmpty()){
            st.push(tmp.pop());
        }
    }
    public static void main(String[] args){
        Stack<Integer> st = new Stack<>();
        st.add(7);
        st.add(8);
        st.add(5);
        st.add(4);
        st.add(1);
        st.add(9);
        st.add(2);
        st.add(6);
        st.add(3);
        st.add(0);
        SortStack.sort(st);
        System.out.println(st.toString());
    }
}
