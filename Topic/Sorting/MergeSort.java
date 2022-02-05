package Topic.Sorting;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Merge Sort: 
 * 
 * Divide and conquer, separate an array into two half subarray.
 * Merge subarray when the subarray only contains two items.
 * then merge two sorted subarray containing two items, and so on.
 * 
 * Best Time Complexity: O(nlogn)
 * Avg Time Complexity: O(nlogn)
 * Worst Time Complexity: O(nlogn)
 * Space Complexity: O(n)
 * 
 */

public class MergeSort {
    public static void sort(int[] array){
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int left, int right){
        if (right <= left)
            return;
        int mid = left + (right - left)/2;
        sort(array, left, mid);
        sort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right){
        int i = 0, j = 0;
        int[] new_arr = new int[right - left + 1];
        while (i < mid - left + 1 && j < right - mid){
            if (array[left + i] < array[mid + 1 + j]){
                new_arr[i + j] = array[left + i];
                i++;
            } else {
                new_arr[i + j] = array[mid + 1 + j];
                j++;
            }
        }
        if (i == mid - left + 1){
            while (j < right - mid){
                new_arr[i + j] = array[mid + 1 + j];
                j++;
            }
        } else if (j == right - mid){
            while (i < mid - left + 1){
                new_arr[i + j] = array[left + i];
                i++;
            }
        }
        System.arraycopy(new_arr, 0, array, left, right-left+1);
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
