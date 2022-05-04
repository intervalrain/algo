package src.Topic.TreesAndGraphs;

/**
 * A heap tree is a complete binary tree (that is, totally filled other than the rightmost elements on the last level)
 * where each node is smaller than its children. The root, therefore is the minimum element in the tree.
 * two key operations: 
 * (1) insert
 * (2) extract
 * 
 * implementation: 
 * 1. insert
 *    (1) put to the last
 *    (2) heapifyUp
 * 2. extract
 */

public class BinaryHeaps {
    abstract class Heap {
        static final int DEFAULT_CAPACITY = 1 << 4;     // aka 16

        int[] array;
        int size;
        int capacity;

        public Heap(int n){
            this.capacity = n;
            array = new int[capacity];
        }

        public Heap(int[] arr, int n){
            n = Math.max(arr.length, n);
            this.capacity = n;
            array = new int[capacity];
            buildHeap(arr);
        }

        public Heap(int[] arr){
            this(arr, DEFAULT_CAPACITY);
        }

        private void resize(){
            this.capacity <<= 1;
            int[] newHeap = new int[capacity];
            System.arraycopy(array, 0, newHeap, 0, size);
            this.array = newHeap;
        }

        public void buildHeap(int[] arr){
            int n = arr.length;
            for (int i = 0; i < n; i++)
                insert(arr[i]);
        }

        private void swap(int a, int b){
            int tmp = array[a];
            array[a] = array[b];
            array[b] = tmp;
        }

        public void heapify(int i){
            int n = size;
            int top = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n && comp(array[left], array[top]))
                top = left;
            if (right < n && comp(array[right], array[top]))
                top = right;
            if (top != i){
                swap(top, i);
                heapify(top);
            }
        }

        public abstract boolean comp(int a, int b);

        private void insert(int val){
            if (size + 1 > capacity) resize();
            array[size] = val;
            size++;
            for (int i = (size + 1)/2; i >= 0; i--)
                heapify(i);
        }

        private int extract(){
            int peek = array[0];
            array[0] = array[size-1];
            array[size-1] = 0;
            size--;
            heapify(0);

            return peek;
        }

        public void push(int val){
            insert(val);
        }

        public int pop(){
            return extract();
        }

        public int peek(){
            return array[0];
        }
    }
    public class minHeap extends Heap{
        
        public minHeap(int n) {
            super(n);
        }

        public minHeap(int[] arr, int n) {
            super(arr, n);
        }

        public minHeap(int[] arr) {
            super(arr);
        }

        @Override
        public boolean comp(int a, int b){
            return a < b;
        }
    }

    public class maxHeap extends Heap{
        
        public maxHeap(int n) {
            super(n);
        }

        public maxHeap(int[] arr, int n) {
            super(arr, n);
        }

        public maxHeap(int[] arr) {
            super(arr);
        }

        @Override
        public boolean comp(int a, int b){
            return a > b;
        }
    }

    public static void main(String[] args){
        int[] arr = new int[]{7,3,2,6,8,5,1,4,9};
        BinaryHeaps clz = new BinaryHeaps();
        maxHeap heap1 = clz.new maxHeap(arr);
        while(heap1.size > 0){
            System.out.print(heap1.pop() + " ");
        }
        System.out.println();
        minHeap heap2 = clz.new minHeap(arr);
        while(heap2.size > 0){
            System.out.print(heap2.pop() + " ");
        }
        System.out.println();
    }

}
