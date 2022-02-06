package Topic.Sorting;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import DioUtility.arrayGenerator;

/**
 * Tim Sort: 
 * 
 * Tim sort is an algorithm based on insertion sort and merge sort.
 * split arrays into subarray insmall pieces (a run which size = 32 or power of 2 to optimize following merge),
 * then merge the equal size sub array into bigger array.
 * 
 * 
 * Best Time Complexity: O(n)
 * Avg Time Complexity: O(nlogn)
 * Worst Time Complexity: O(nlogn)
 * Space Complexity: O(n)
 * 
 */

public class TimSort {

    public static int MIN_MERGE = 32;

    public static void sort(int[] array){
        int minRun = minRunLength(MIN_MERGE);
        // sort individual subarray of size RUN.
        int n = array.length;
        for (int i = 0; i < n; i += minRun){
            int hi = Math.min((i + MIN_MERGE - 1), n - 1);
            insertionSort(array, i, hi);
        }

        //start merging from size RUN = 32, 64, 128, ...
        for (int size = minRun; size < n; size *= 2){
            for (int left = 0; left < n; left += 2 * size){
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), n-1);
                if (mid < right){
                    merge(array, left, mid, right);
                }
            }
        }
    }

    public static void merge(int[] array, int l, int m, int r){
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++)
        {
            left[x] = array[l + x];
        }
        for (int x = 0; x < len2; x++)
        {
            right[x] = array[m + 1 + x];
        }
 
        int i = 0;
        int j = 0;
        int k = l;
 
        // After comparing, we merge those two array
        // in larger sub array
        while (i < len1 && j < len2)
        {
            if (left[i] <= right[j])
            {
                array[k] = left[i];
                i++;
            }
            else {
                array[k] = right[j];
                j++;
            }
            k++;
        }
 
        // Copy remaining elements
        // of left, if any
        while (i < len1)
        {
            array[k] = left[i];
            k++;
            i++;
        }
 
        // Copy remaining element
        // of right, if any
        while (j < len2)
        {
            array[k] = right[j];
            k++;
            j++;
        }
    }

    public static void insertionSort(int[] array, int left, int right){
        int i, j;
        for (i = left + 1; i <= right; i++){
            int now = array[i];
            for (j = i - 1; j >= 0 && now < array[j]; j--){
                array[j+1] = array[j];
            }
            array[j + 1] = now;
        }
    }

    private static int minRunLength(int n){
        assert n >= 0;
        int r = 0;
        while (n >= MIN_MERGE){
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;

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
