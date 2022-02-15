package Topic.StackAndQueues;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/** Describe how you could use a single array to implement three stacks */

public class ThreeInOne {

    private static int DEFAULT_STACK_NUMS = 3;
    private static int DEFAULT_STACK_SIZE = 5;

    private int numberOfStacks;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public ThreeInOne(){
        this(DEFAULT_STACK_SIZE, DEFAULT_STACK_NUMS);
    }

    public ThreeInOne(int stackNums){
        this(DEFAULT_STACK_SIZE, stackNums);
    }
    public ThreeInOne(int stackNums, int stackSize){
        stackCapacity = stackSize;
        numberOfStacks = stackNums;
        values = new int[stackSize * numberOfStacks];
        sizes = new int[stackNums];
    }

    public void push(int stackNum, int value){
        if (isFull(stackNum)){
            resize();
        }
        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    public int pop(int stackNum){
        if (isEmpty(stackNum)){
            throw new EmptyStackException();
        }

        int topIndex = indexOfTop(stackNum);
        int value = values[topIndex];
        values[topIndex] = 0;
        sizes[stackNum]--;
        return value;
    }

    public int peek(int stackNum){
        if (isEmpty(stackNum)){
            throw new EmptyStackException();
        }
        return values[indexOfTop(stackNum)];
    }

    public boolean isEmpty(int stackNum){
        return sizes[stackNum] == 0;
    }

    public boolean isFull(int stackNum){
        return sizes[stackNum] == stackCapacity;
    }

    private int indexOfTop(int stackNum){
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum];
        return offset + size - 1;
    }

    public void clear(int stackNum){
        int size = sizes[stackNum];
        while (size > 0){
            pop(stackNum);
            size--;
        }
    }

    public void init(){
        values = new int[stackCapacity * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    public void resize(){
        int[] new_values = new int[2 * stackCapacity * numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++){
            int offset = i * stackCapacity;
            System.arraycopy(values, offset, new_values, 2 * offset, stackCapacity);
        }
        stackCapacity *= 2;
        values = new_values;
    }

    public List<List<Integer>> asList(){
        List<List<Integer>> outer = new ArrayList<>();
        for (int i = 0; i < numberOfStacks; i++){
            List<Integer> inner = new ArrayList<>();
            int offset = stackCapacity * i;
            for (int j = 0; j < sizes[i]; j++){
                inner.add(values[offset + j]);
            }
            outer.add(inner);
        }
        return outer;
    }


    public static void main(String[] args){
        ThreeInOne stack = new ThreeInOne(3, 5);
        stack.push(0, 1);
        stack.push(0, 2);
        stack.push(0, 3);
        stack.push(0, 4);
        stack.push(0, 5);
        stack.push(1, 5);
        stack.push(1, 4);
        stack.push(1, 3);
        stack.push(1, 2);
        stack.push(1, 1);
        stack.push(2, 3);
        stack.push(2, 3);
        stack.push(2, 3);
        stack.push(2, 3);
        stack.push(2, 3);
        stack.pop(0);
        stack.pop(0);
        stack.pop(0);
        stack.pop(1);
        stack.pop(1);
        stack.pop(2);
        stack.pop(2);
        stack.pop(2);
        stack.pop(2);
        List<List<Integer>> list = stack.asList();
        System.out.println(list.toString());
    }
}
