package DioUtility.Generic;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> {
    private static class Node<T>{
        private T val;
        private Node<T> next;
        public Node(T val){
            this.val = val;
        }
    }

    private Node<T> top;


    public T pop(){
        if (top == null)
            throw new EmptyStackException();
        T item = top.val;
        top = top.next;
        return item;
    }

    public void push(T item){
        Node<T> node = new Node<>(item);
        node.next = top;
        top = node;
    }

    public T peek(){
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

    public Object[] toArray(){
        Node<T> curr = top;
        int n = size();
        Object[] array = new Object[n];
        while (curr != null){
            array[--n] = curr.val;
            curr = curr.next;
        }
        return array;
    }

    public int size(){
        Node<T> curr = top;
        int cnt = 0;
        while (curr != null){
            cnt++;
            curr = curr.next;
        }
        return cnt;
    }

}
