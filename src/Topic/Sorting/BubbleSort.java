package src.Topic.Sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

import src.DioUtility.DioInt.arrayGenerator;

/**
 * Bubble Sort:
 * 
 * Swap two nearest item if the previous one is bigger.
 * One value will pop up from the last index to the first index each round.
 * 
 * Best Time Complexity: O(n)
 * Avg Time Complexity: O(n^2)
 * Worst Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */

public class BubbleSort {
    public static void sort(int[] array){
        for (int i = 0; i < array.length - 1; i++){
            for (int j = 1; j < array.length - i; j++){
                if (array[j - 1] > array[j]){
                    int tmp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = tmp;
                }
            }
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
