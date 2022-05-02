package src.DioUtility.Topic.StackAndQueues;

import java.util.EmptyStackException;

/**
 * Multistack is flexible in size. When one stack exceeds its initial capacity,
 * we grow the allowable capacity and shift elements as necessary.
 * 
 * We will design out array to be circular,
 * such that the final stack may start at the end of the array and wrap around to the beginning.
 *
 */

public class MultiStack {
    /**
     * StackInfo is a simple class that holds a set of data about each stack.
     * It does not hold the actual items in the stack.
     * We could have done this with just a bunch of individual variables,
     * but that's messy and doesn't gain us much 
     */ 
    private class stackInfo{
        public int start;
        public int size;
        public int cap;
        public stackInfo(int start, int cap){
            this.start = start;
            this.cap = cap;
        }

        // Check if an index on the full array is within the stack boundaries.
        // The stack can wrap around to the start of the array.
        public boolean isWithinStackCapacity(int idx) {
            // if outside the bounds of array, return false.
            if (idx < 0 || idx >= values.length)
                return false;
            // if index wraps around, adjust it.
            int contiguousIndex = idx < start ? idx + values.length : idx;
            int end = start + cap;
            return start <= contiguousIndex && contiguousIndex < end;
        }

        public int lastCapacityIndex() {
            return adjustIndex(start + cap - 1);
        }

        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
        }

        public boolean isFull(){
            return size == cap;
        }
        public boolean isEmpty(){
            return size == 0;
        }
    }

    /** Fields */
    private stackInfo[] info;
    private int[] values;
    
    public MultiStack(int n, int defaultSize) {
        // create meta data for all the stacks
        info = new stackInfo[n];
        for (int i = 0; i < n; i++)
            info[i] = new stackInfo(defaultSize * i, defaultSize);
        values = new int[n * defaultSize];

    }

    // push value onto stack, shifting/expanding stack as necessary.
    // Throws exception if all stack are full.
    public void push(int idx, int val) throws FullStackException{
        if (allStacksAreFull()) throw new FullStackException();
        stackInfo stack = info[idx];
        if (stack.isFull()) expand(idx);
        stack.size++;
        values[stack.lastElementIndex()] = val;
    }

    public int pop(int idx) throws Exception {
        stackInfo stack = info[idx];
        if (stack.isEmpty()) throw new EmptyStackException();
        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0;
        stack.size--;
        return value;
    }

    public int peek(int idx) {
        stackInfo stack = info[idx];
        return values[stack.lastElementIndex()];
    }
    
    // shift items in stack over one by one element.
    // If we have availble capacity, then we'll end up shrinking the stack by one element.
    // If we don't have available capacity, then we'll need to shift the next stack over too.
    private void shift(int idx) {
        System.out.println("/// Shifting " + idx);
        stackInfo stack = info[idx];
        if (stack.size >= stack.cap){
            int nextStack = (idx + 1 % info.length);
            shift(nextStack);
            stack.cap++;
        }
        int index = stack.lastCapacityIndex();
        while (stack.isWithinStackCapacity(idx)){
            values[index] = values[previousIndex(idx)];
            index = previousIndex(idx);
        }
        values[stack.start] = 0;
        stack.start = nextIndex(stack.start);
        stack.cap--;        
    }

    private void expand (int idx) {
        shift((idx + 1) % info.length);
        info[idx].cap++;
    }

    public int numberOfElements() {
        int size = 0;
        for (stackInfo sd : info){
            size += sd.size;
        }
        return size;
    }

    public boolean allStacksAreFull() {
        return numberOfElements() == values.length;
    }

    // Adjust index to be within the range of 0 -> length - 1
    private int adjustIndex(int idx) {
        int max = values.length;
        return ((idx % max) + max) % max; // to ensure idx is positive
    }

    private int nextIndex(int idx) {
        return adjustIndex(idx + 1);
    }

    private int previousIndex(int idx) {
        return adjustIndex(idx - 1);
    }

    public static void main(String[] args) throws FullStackException{
        MultiStack stack = new MultiStack(3, 3);
        stack.push(1, 1);
        stack.push(1, 2);
        stack.push(1, 3);
        stack.push(1, 4);
    }
}

