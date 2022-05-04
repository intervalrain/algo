package src.Topic.StackAndQueues;

import java.util.ArrayList;
import java.util.List;

/**
 * Imagine a literal stack of plates.
 * If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold.
 * Implement a data structure SetOfStacks that mimics this.
 * SetOfStacks should be composed of several stacks and should create a new stack once the previous one exceeds capacity.
 * SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single stack
 * (that is, pop() should return the same values as it would if there were just a single stack).
 * 
 * 
 *  ^   ^   ^  top
 *  ^   ^   ^
 *  ^   ^   ^
 *  ^   ^   ^
 */

public class SetOfStacks {
    
    // default values
    static int DEFAULT_CAPACITY = 3;

    // Inner class
    private class Stack {

        private class Node {
            int val;
            Node prev, next;
            Node (int val){
                this.val = val;
                this.next = null;
                this.prev = null;
            }
            public String toString(){
                return Integer.toString(val);
            }
        }

        // Fields
        Node top, bot;
        int size;

        // Constructor
        Stack(int val){
            this.top = new Node(val);
            bot = top;
            size++;
        }
        
        boolean isEmpty(){
            return this.bot == null || this.size == 0;
        }

        boolean isFull(){
            return this.size == capacity;
        }

        void push(int val){
            Node node = new Node(val);
            node.prev = top;
            top.next = node;
            top = node;
            size++;
        }

        int pop() throws NullPointerException{
            if (top == null || size == 0)
                throw new NullPointerException();
            int tmp = top.val;
            if (top.prev != null){
                top = top.prev;
                top.next = null;
            } else {
                top = null;
            }
            size--;
            return tmp;
        }

        int peek() throws NullPointerException{
            if (stacks == null || stacks.size() == 0)
                throw new NullPointerException();
            return top.val;
        }

        Node getNode(int index){
            if (index > size) return null;
            Node curr = bot;
            while (index > 0){
                curr = curr.next;
                index--;
            }
            return curr;
        }

        public String toString(){
            Node curr = bot;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while (curr != null){
                sb.append(curr.val + ",");
                curr = curr.next;
            }
            return sb.substring(0, sb.length()-1) + "]";
        }
    }

    // Fields
    List<Stack> stacks;
    final int capacity;

    // Constructor
    public SetOfStacks(int initCap) throws IllegalArgumentException{
        if (initCap <= 0) throw new IllegalArgumentException("Initial capacity cannot be less than 1.");
        this.capacity = initCap;
        stacks = new ArrayList<>();
    }
    public SetOfStacks(){
        this(DEFAULT_CAPACITY);
    }

    // Private Methods
    private Stack getLastStack(){
        if (stacks == null || stacks.size() == 0)
            return null;
        return stacks.get(stacks.size() - 1);
    }

    private void removeLast(){
        stacks.remove(stacks.size() - 1);
    }

    private boolean checkInBound(int index){
        if (index < 0) return false;
        int alen;
        if ((alen = index / capacity) > stacks.size()) return false;
        if (alen == stacks.size() && ((index % capacity)) > getLastStack().size) return false;
        return true;
    }

    private Stack getStack(int index){
        if (index >= stacks.size()) return null;
        return stacks.get(index);
    }

    private int leftShift(int index){
        int n = index / capacity;
        int idx = index % capacity;
        Stack st = getStack(n);
        Stack.Node node = st.getNode(idx);
        int value = node.val;
        if (node.prev == null){
            node = node.next;
            if (node != null) node.prev = null;
            st.bot = st.bot.next;
        } else if (node.next == null){
            st.top = st.top.prev;
            node.prev.next = null;
        } else {
            node.prev = node.next;
        }
        
        if (st.isEmpty()){
            stacks.remove(n);
        } else if (stacks.size() > n + 1){
            int tmp = leftShift((n+1)*capacity);
            st.push(tmp);
        }
        return value;
    }

    // Methods
    public void push(int val){
        Stack last = getLastStack();
        if (last != null && !last.isFull()){
            last.push(val);
        } else {
            Stack stack = new Stack(val);
            stacks.add(stack);
        }
    }

    public int pop() throws NullPointerException{
        Stack last = getLastStack();
        if (last == null || stacks.size() == 0)
            throw new NullPointerException();
        int val = last.pop();
        if (last == null || last.isEmpty())
            removeLast();
        return val;
    }

    public int peekAt(int index) throws NullPointerException{
        if (!checkInBound(index)) throw new NullPointerException();
        int n = index / capacity;
        int idx = index % capacity;
        Stack st = getStack(n);
        return st.getNode(idx).val;
    }

    public int peek() throws NullPointerException{
        Stack stack = getLastStack();
        if (stack == null || stack.size == 0) throw new NullPointerException();
        return stack.peek();
    }

    public int popAt(int index) throws NullPointerException{
        if (!checkInBound(index)) throw new NullPointerException();
        int tmp = peekAt(index);
        leftShift(index);
        return tmp;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Stack st : stacks){
            String s = st.toString();
            sb.append(s.substring(1, s.length()-1));
            sb.append(",");
        }
        return "[" + sb.substring(0, sb.length()-1) + "]";
    }

    public static void main(String[] args){
        SetOfStacks stack = new SetOfStacks();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        stack.popAt(3);
        stack.popAt(5);
        stack.popAt(0);
        stack.popAt(6);
        System.out.println(stack.toString());
    }
}