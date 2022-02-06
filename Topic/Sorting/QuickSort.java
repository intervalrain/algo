package Topic.Sorting;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import DioUtility.arrayGenerator;

/**
 * Quick Sort: 
 * 
 * Picking a pivot element from the array and partitioning the remaining elements into two subarrays.
 * Two subarrays are based on whether they are greater or less than the pivot.
 * Quicksort has a better locality of reference than merge sort, 
 * so that acceess are usually faster than merge sort accesses.
 * 
 * Best Time Complexity: O(nlogn)
 * Avg Time Complexity: O(nlogn)
 * Worst Time Complexity: O(n^2)
 * Space Complexity: O(logn)
 * 
 */

public class QuickSort {
    
    public void swap(int[] array, int from, int to){
        int tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }

    public void sort(int[] array){
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int left, int right){
        if (left >= right) return;
        int part = partition(array, left, right);
        sort(array, left, part - 1);
        sort(array, part + 1, right);
    }

    private int partition(int[] array, int left, int right){
        int pivot = right;
        while (left < right){
            while (array[left] < array[pivot])
                left++;
            while (array[right] > array[pivot])
                right--;
            if (left < right)
                swap(array, left, right);
        }
        if (left == right && (array[left] > array[pivot] || array[right] < array[pivot])){
            swap(array, left, pivot);
            return left;
        }
        return pivot;
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

     @Test
    public void randomTest(){
        int[] array    = new arrayGenerator(128, false).toArray();
        sort(array);
        int[] expected = new arrayGenerator(128, true).toArray();
        assertArrayEquals(expected, array);
    }
}
