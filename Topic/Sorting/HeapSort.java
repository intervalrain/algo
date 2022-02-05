package Topic.Sorting;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Heap Sort: 
 * 
 * Sort by a binary heap, 
 * construct a min-heap
 * when parent's index is a, its children 
 *   left  = 2a+1;
 *   right = 2a+2.
 * 
 * change expression by array's size = n.
 *   if n is odd, 
 *   parent = n/2
 *   left   = n
 *   right  = null
 * 
 *   if n is even,
 *   parent = n/2 - 1
 *   left   = n - 1
 *   right  = n
 * 
 * Heapify: construct max heap, then poll top, drag the last item as new top then downshift with the max heap rule.
 * 
 * 
 * Best Time Complexity: O(nlogn)
 * Avg Time Complexity: O(nlogn)
 * Worst Time Complexity: O(nlogn)
 * Space Complexity: O(n)
 * 
 */
public class HeapSort {
    
    public void sort(int[] array){
        int n = array.length;
        // constuct max heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);
        // swap the top to the last, and let the last item as sorted.
        for (int i = n - 1; i >= 0; i--){
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            heapify(array, i, 0);
        }
    }

    public void heapify(int[] array, int n, int i){
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && array[left] > array[max])
            max = left;
        if (right < n && array[right] > array[max])
            max = right;
        if (max != i){
            int tmp = array[i];
            array[i] = array[max];
            array[max] = tmp;
            heapify(array, n, max);
        }
    }
    
    @Test
    public void test1(){
        int[] array = new int[]{3,5,7,8,4,2,1,9,6};
        sort(array);
        int[] expected = new int[]{1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expected, array);
    }

    @Test
    public void test2(){
        int[] array = new int[]{9,8,7,6,5,4,3,2,1};
        sort(array);
        int[] expected = new int[]{1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expected, array);
    }
}
