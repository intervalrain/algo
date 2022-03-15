package Topic.StackAndQueues;

import java.util.EmptyStackException;

public class ThreeInOne {
    // divide array into n parts.
    final static int n = 3;
    private int cap;
    private int[] values;
    private int[] sizes;

    public ThreeInOne(int stackCap){
        cap = stackCap;
        int len = n * cap;
        values = new int[len];
        sizes = new int[n];
    }

    public void push(int idx, int val) throws FullStackException {
        if (isFull(idx)) throw new FullStackException();
        values[indexOfTop(idx)] = val;
        sizes[idx]++;
    }

    public int pop(int idx){
        if (isEmpty(idx))
            throw new EmptyStackException();
        int topIndex = indexOfTop(idx);
        int value = values[topIndex];
        values[topIndex] = 0;
        sizes[idx]--;
        return value;
    }

    public int peek(int idx){
        if (isEmpty(idx))
            throw new EmptyStackException();
        return indexOfTop(idx);
    }

    public boolean isEmpty(int idx){
        return sizes[idx] == 0;
    }

    public boolean isFull(int idx){
        return sizes[idx] == cap;
    }

    // 0,1,2,
    // 3,4,5,
    // 6,7,8
    private int indexOfTop(int idx){
        int offset = idx * cap;
        int size = sizes[idx];
        return offset + size;
    }
}
