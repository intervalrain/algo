package Topic.Sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

import DioUtility.arrayGenerator;

/**
 * Shell Sort:
 * 
 * A variation of insertion sort, we move elements only one position ahead.
 * When an element has to be moved far ahead, many movements are involved.
 * The idea of shell sort is to allow exchange of items.
 * we make the array h-sorted for a large value of h, and keep reducing it until h becomes 1.
 * 
 * 
 * Best Time Complexity: O(nlogn)
 * Avg Time Complexity: O(n(logn)^2)
 * Worst Time Complexity: O(n(logn)^2)
 * Space Complexity: O(1)
 */

public class ShellSort {
    
    public static void sort(int[] array){
        int n = array.length;
        for (int gap = n/2; gap > 0; gap /= 2){
            for (int i = gap; i < n; i++){
                int tmp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > tmp; j -= gap){
                    array[j] = array[j - gap];
                }
                array[j] = tmp;
            }
        }
    }

    /**
     * Demo: 3,5,7,8,4,2,1,9,6
     * 
     *  3,5,7,8
     *  4,2,1,9
     *  6
     * 
     *  3,2,1,8    -> 3,2,1,8,4,5,7,9,6
     *  4,5,7,9
     *  6
     * 
     *  3,2,
     *  1,8,
     *  4,5,
     *  7,9
     *  6
     * 
     *  1,2,   -> 1,2,3,5,4,8,6,9,7
     *  3,5,
     *  4,8,
     *  6,9
     *  7
     * 
     *  1,   ->   1,2,3,4,5,6,7,8,9
     *  2,     
     *  3,
     *  5,
     *  4,
     *  8,
     *  6,
     *  9,
     *  7
     */

    @Test
    public void test1(){
        int[] array = new int[]{3,5,7,8,4,2,1,9,6};
        sort(array);
        int[] expected = new int[]{1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expected, array);
    }

    /**
     * Demo: 
     * (1) 9 8 7 6 5 4 3 2 1
     * 9 8 7 6    1 4 3 2
     * 5 4 3 2 -> 5 8 7 6
     * 1          9
     * 
     * (2) 5 4 3 2 1 8 7 6 9
     * 5 4    1 2
     * 3 2    3 4
     * 1 8 -> 5 6 -> done.
     * 7 6    7 8
     * 9      9
     * 
     */
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
