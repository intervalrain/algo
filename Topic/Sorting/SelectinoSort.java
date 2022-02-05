package Topic.Sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

/**
 * Selection Sort:
 * 
 * Find the minimum value in remaining array.
 * Fix one item each round from the first index to the last index.
 * Swap the min value with the value of the stagged index.
 * 
 * Best Time Complexity: O(n^2)
 * Avg Time Complexity: O(n^2)
 * Worst Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */

public class SelectinoSort {
    public static void sort(int[] array){
        for (int i = 0; i < array.length - 1; i++){
            int min = i;
            for (int j = i + 1; j < array.length; j++){
                min = array[min] > array[j] ? j : min;
            }
            int tmp = array[min];
            array[min] = array[i];
            array[i] = tmp;
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
