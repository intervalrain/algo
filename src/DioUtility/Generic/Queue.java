package src.DioUtility.Generic;


import java.util.Arrays;
import java.util.Collection;

import java.util.NoSuchElementException;

public class Queue<T> {
    private static class Node<T>{
        private T val;
        private Node<T> next;

        public Node(T val){
            this(val, null);
        }

        public Node(T val, Node<T> next){
            this.val = val;
            this.next = next;
        }
    }

    private int size;
    private Node<T> first;
    private Node<T> last;

    public Queue(){
        size = 0;
        first = null;
        last = null;
    }

    public Queue(Collection<? extends T> c){
        this();
        addAll(c);
    }

    public void add(T val){
        Node<T> node = new Node<>(val);
        if (last != null)
            last.next = node;
        last = node;
        if (first == null)
            first = last;
        size++;
    }
    
    public boolean addAll(Collection<? extends T> c){
        return addAll(size, c);
    }

    private Node<T> getNode(int index){
        int cnt = 0;
        Node<T> curr = first;
        while (cnt != index){
            curr = curr.next;
            cnt++;
        }
        return curr;
    }

    public boolean addAll(int index, Collection<? extends T> c){
        checkPositionIndex(index);

        Object[] array = c.toArray();
        int len = array.length;
        if (len == 0)
            return false;

        Node<T> pred, succ;
        if (index == size) {
            pred = last;
            succ = null;
        } else {
            pred = getNode(index-1);
            succ = pred.next;
        }

        for (Object o : array) {
            @SuppressWarnings("unchecked") T e = (T) o;
            Node<T> newNode = new Node<>(e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
        }

        size += len;
        return true;

    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    public T remove(){
        if (first == null) throw new NoSuchElementException();

        T val = first.val;
        first = first.next;
        if (first == null)
            last = null;
        return val;
    }
    
    public T peek(){
        if (first == null) throw new NoSuchElementException();
        return first.val;
    }
    
    public T pull(){
        return remove();
    }
    
    public void push(T val){
        add(val);
    }
    
    public boolean isEmpty(){
        return first == null;
    }

    @Override
    public String toString(){
        return Arrays.toString(toArray());
    }

    public Object[] toArray(){
        Node<T> curr = first;
        int n = 0;
        Object[] array = new Object[size()];
        while (curr != null){
            array[n++] = curr.val;
            curr = curr.next;
        }
        return array;

    }

    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    public int size(){
        return size;
    }

    public void clear() {
        for (Node<T> x = first; x != null; x = x.next) {
            x.val = null;
            x.next = null;
        }
        first = last = null;
        size = 0;
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<T> x = first; x != null; x = x.next) {
                if (x.val == null)
                    return index;
                index++;
            }
        } else {
            for (Node<T> x = first; x != null; x = x.next) {
                if (o.equals(x.val))
                    return index;
                index++;
            }
        }
        return -1;
    }
}
