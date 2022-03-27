package Topic.StackAndQueues;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

import DioUtility.Generic.ListNode;

import java.util.NoSuchElementException;


public class QueueViaStack<T>{

    // Fields
    int size;
    Stack<T> oldst;
    Stack<T> newst;

    // Constructor
    public QueueViaStack(){
        this.size = 0;
        oldst = new Stack<T>();
        newst = new Stack<T>();
    }

    private void shiftStacks(){
        shiftStacks(newst, oldst, null);
    }

    @SuppressWarnings("unchecked")
    private boolean shiftStacks(Stack<T> from, Stack<T> to, Object toFind){
        boolean flag = false;
        while (!from.isEmpty()){
            Object top = from.pop();
            to.push((T)top);
            if (top == toFind) flag = true;
        }
        return flag;
    }

    private void arrange(){
        contains(null);
    }

    public boolean contains(Object o) {
        Stack<T> tmp = new Stack<T>();
        if (shiftStacks(oldst, tmp, o) ||
            shiftStacks(newst, oldst, o) ||
            shiftStacks(tmp, oldst, o))
            return true;
        return false;
    }

    // methods
    public boolean add(T e) {
        newst.push(e);
        size++;
        return false;
    }

    public boolean offer(T e) {
        return add(e);
    }

    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        arrange();
        boolean res = false;
        while (!oldst.isEmpty()){
            Object toFind = oldst.pop();
            if (toFind == o){
                res = true;
                break;
            }
            newst.push((T)toFind);
        }
        shiftStacks(oldst, newst, null);
        shiftStacks(newst, oldst, null);
        size--;
        return res;
    }

    public T remove() {
        if (size == 0)
            throw new NoSuchElementException();
        shiftStacks();
        size--;
        return oldst.pop();
    }

    public T poll() {
        if (size == 0)
            return null;
        return remove();
    }

    public T element() {
        if (size == 0) throw new NoSuchElementException();
        return peek();
    }

    public T peek() {
        if (size == 0) return null;
        shiftStacks();
        return oldst.peek();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<T> iterator() {
        return new stackIterator<>();
    }

    @SuppressWarnings("unchecked")
    private class stackIterator<E> implements Iterator<E>{
        ListNode<E> list;
        stackIterator(){
            arrange();
            E e = (E)oldst.pop();
            list = new ListNode<E>(e);
            newst.add((T)e);
            ListNode<E> curr = list;
            while (!oldst.isEmpty()){
                e = (E)oldst.pop();
                curr.next = new ListNode<E>(e);
                newst.add((T)e);
                curr = curr.next;
            }
        }

        @Override
        public boolean hasNext() {
            return list.hasNext();
        }

        @Override
        public E next() {
            E e = list.val;
            list = list.next;
            return e;
        }
    }

    public Object[] toArray() {
        if (size == 0) return new Object[]{};
        Iterator<T> iter = iterator();
        Object[] array = new Object[size];
        int i = 0;
        while (i < size){
            array[i++] = iter.next();
        }
        arrange();
        return array;
    }

    // public <T> T[] toArray(T[] a) {
    //     return null;
    // }

    // public boolean containsAll(Collection<?> c) {
    //     return false;
    // }

    // public boolean addAll(Collection<? extends T> c) {
    //     return false;
    // }

    // public boolean removeAll(Collection<?> c) {
    //     return false;
    // }

    // public boolean retainAll(Collection<?> c) {
    //     return false;
    // }

    public void clear() {
        oldst = new Stack<T>();
        newst = new Stack<T>();
        size = 0;
        System.gc();
    }

    public static void main(String[] args){
        QueueViaStack<Integer> st = new QueueViaStack<>();
        st.add(0);
        st.add(1);
        st.add(2);
        st.add(3);
        st.add(4);
        st.add(5);
        st.add(6);
        System.out.println(st.contains(5));
        System.out.println(st.contains(7));
        System.out.println(st.remove(4));
        st.arrange();
        System.out.println(Arrays.toString(st.toArray()));
        System.out.println(Arrays.toString(st.toArray()));
        System.out.println(st.peek());
        System.out.println(st.poll());
        System.out.println(st.element());
    }

}
