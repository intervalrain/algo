package Topic.Sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

import DioUtility.arrayGenerator;

/**
 * Insertion Sort:
 * 
 * Iterate from first index to last index, 
 * shift ahead if the previous one is bigger less than itself,
 * stop until encounter a smaller one.
 * 
 * Best Time Complexity: O(n)
 * Avg Time Complexity: O(n^2)
 * Worst Time Complexity: O(n^2)
 * 
 * Space Complexity: O(1)
 */
public class InsertionSort {
    public static void sort(int[] array){
        int i, j;
        for (i = 1; i < array.length; i++){
            int now = array[i];
            for (j = i - 1; j >= 0 && now < array[j]; j--){
                array[j+1] = array[j];
            }
            array[j + 1] = now;
            System.out.println(Arrays.toString(array));
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
