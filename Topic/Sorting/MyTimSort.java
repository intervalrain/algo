package Topic.Sorting;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import DioUtility.arrayGenerator;


/**
 * My Tim Sort implementaion 
 * 
 * Tim sort is an algorithm based on insertion sort and merge sort.
 * split arrays into subarray insmall pieces (a run which size equals 16)
 * then merge the equal size sub array into bigger array.
 * 
 * 
 * Best Time Complexity: O(n)
 * Avg Time Complexity: O(nlogn)
 * Worst Time Complexity: O(nlogn)
 * Space Complexity: O(n)
 * 
 */

public class MyTimSort {

    static int n = 16;

    public void sort(int[] array){
        for (int i = 0; i < array.length; i += n){
            insertionSort(array, i, Math.min(i + n - 1, array.length - 1));
        }

        int interval = n;
        while (interval < array.length){
            for (int i = 0; i < array.length; i += 2*interval){
                merge(array, i, Math.min(i + interval - 1, array.length - 1), Math.min((i + 2 * interval - 1), array.length - 1));
            }
            interval *= 2;
        }
        
    }

    private void merge(int[] array, int left, int mid, int right){
        int i = 0, j = 0;
        int len1 = mid - left + 1;
        int len2 = right - mid;
        int[] new_arr = new int[right - left + 1];
        while (i < len1 && j < len2){
            if (array[left + i] <= array[mid + j + 1]){
                new_arr[i + j] = array[left + i];
                i++;
            } else {
                new_arr[i + j] = array[mid + j + 1];
                j++;
            }
        }
        if (i == len1){
            while (j < len2){
                new_arr[i + j] = array[mid + j + 1];
                j++;
            }
        } else {
            while (i < len1){
                new_arr[i + j] = array[left + i];
                i++;
            }
        }
        System.arraycopy(new_arr, 0, array, left, new_arr.length);
    }

    public void insertionSort(int[] array, int left, int right){
        int i, j;
        for (i = left + 1; i <= right; i++){
            int curr = array[i];
            for (j = i - 1; j >= left && curr < array[j]; j--){
                array[j + 1] = array[j];
            }
            array[j + 1] = curr;
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

    @Test
    public void randomTest(){
        int[] array    = new arrayGenerator(128, false).toArray();
        sort(array);
        int[] expected = new arrayGenerator(128, true).toArray();
        assertArrayEquals(expected, array);
    }
}
