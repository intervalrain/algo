package Topic.Sorting;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

import DioUtility.DioInt.arrayGenerator;
 
/**
 * Radix Sort
 * 
 * Sort input array using counting sort(or any other stable sort) according to the i'th digit.
 * Do counting sort for every digit.
 * exp is passed instead of passing digit number. (exp is 10^i where i is current digit number.)
 * 
 * Best Time Complexity: O(nk)
 * Avg Time Complexity: O(nk)
 * Worst Time Complexity: O(nk)
 * Space Complexity: O(n+k)
 */
public class RadixSort {

    private int max;
    private int min;
    private boolean ex_mode;
    private int[] pos;
    private int[] neg;
    private int[] arr;
    

    public void init(){
        max = arr[0];
        for (int i = 1; i < arr.length; i++){
            max = Math.max(max, arr[i]);
            if (arr[i] < 0){
                init_ex();
                ex_mode = true;
                break;
            }
        }
    }

    /** optimize for negative numbers */
    public void init_ex(){
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        int cnt_p = 0;
        int cnt_n = 0;
        for (int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            if (arr[i] >= 0)
                cnt_p++;
            else
                cnt_n++;
        }
        pos = new int[cnt_p];
        neg = new int[cnt_n];
        cnt_n = 0; cnt_p = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] < 0)
                neg[cnt_n++] = arr[i] - min;
            else
                pos[cnt_p++] = arr[i];
        }
    }

    public void sort(int array[]){
        this.arr = array;
        this.ex_mode = false;  // if negs exist, turn on extra mode.
        init();
        if (!ex_mode){
            sort(array, max);
        } else {
            int m;
            m = min == Integer.MIN_VALUE ? Integer.MAX_VALUE : -min;

            sort(pos, max);
            sort(neg, m);
            for (int i = 0; i < neg.length; i++)
                neg[i] += min;
            System.arraycopy(neg, 0, array, 0, neg.length);
            System.arraycopy(pos, 0, array, neg.length, pos.length);
        }
    }
    public void sort(int array[], int m){
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(array, exp);
    }
 
    // A function to do counting sort of arr[] according to the digit represented by exp.
    static void countSort(int[] array, int exp){
        int n = array.length;
        int output[] = new int[n];
        int count[] = new int[10];
 
        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++)
            count[(array[i] / exp) % 10]++;
 
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];
 
        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }
 
        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        for (int i = 0; i < n; i++)
            array[i] = output[i];
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

    @Test
    public void bigRangeTest(){
        int[] array    = new arrayGenerator(20, false, false, -10000, 10000).toArray();
        int[] expected = new int[array.length];
        System.arraycopy(array, 0, expected, 0, array.length);
        sort(array);
        Arrays.sort(expected);
        assertArrayEquals(expected, array);
    }
}