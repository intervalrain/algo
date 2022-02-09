package DioUtility.DioInt;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private static class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val = val;
        }
    }

    private Node top;


    public int pop(){
        if (top == null)
            throw new EmptyStackException();
        int item = top.val;
        top = top.next;
        return item;
    }

    public void push(int item){
        Node node = new Node(item);
        node.next = top;
        top = node;
    }

    public int peek(){
        if (top == null)
            throw new EmptyStackException();
        return top.val;
    }

    public boolean isEmpty(){
        return top == null;
    }

    @Override
    public String toString(){
        return Arrays.toString(toArray());
    }

    public int[] toArray(){
        Node curr = top;
        int n = size();
        int[] array = new int[n];
        while (curr != null){
            array[--n] = curr.val;
            curr = curr.next;
        }
        return array;
    }

    public int size(){
        Node curr = top;
        int cnt = 0;
        while (curr != null){
            cnt++;
            curr = curr.next;
        }
        return cnt;
    }


    public static void main(String[] args){
        Stack st = new Stack();
        st.push(1);
        st.push(2);
        st.push(4);
        st.push(8);
        st.push(16);
        st.pop();
        st.peek();
        System.out.println(st);
    }
}
