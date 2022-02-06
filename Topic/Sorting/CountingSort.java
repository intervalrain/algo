package Topic.Sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

import DioUtility.arrayGenerator;

/**
 * Counting Sort:
 * 
 * Counting sort is a sorting technique based on keys between a specific range. 
 * It works by counting the number of objects having distinct key values (kind of hashing). 
 * Then doing some arithmetic to calculate the position of each object in the output sequence.
 * 
 * Best Time Complexity: O(n+k)
 * Avg Time Complexity: O(n+k)
 * Worst Time Complexity: O(n+k)
 * Space Complexity: O(k)
 */

public class CountingSort {
    
    int max;
    int min;

    public void getExtreme(int[] array){
        for (int i = 0; i < array.length; i++){
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }
    }

    public void sort(int[] array){
        // find max and min of array: O(n)
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        getExtreme(array);

        // create a map for counting amounts of elements. space: O(n), time: O(n)
        int[] count = new int[max - min + 1];

        for (int i = 0; i < array.length; i++){
            count[array[i] - min]++;
        }

        // transfer the map into accumulation map. time: O(k)
        for (int i = 1; i < count.length; i++){
            count[i] += count[i-1];
        }

        // put elements into new array according to map: O(n)
        int[] output = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            output[--count[array[i] - min]] = array[i];
        }

        System.arraycopy(output, 0, array, 0, array.length);
    }


    @Test
    public void testRepeated(){
        arrayGenerator ag = new arrayGenerator(8, false, false);
        int[] array = ag.toArray();
        sort(array);
        int[] expected = new int[array.length];
        System.arraycopy(array, 0, expected, 0, array.length);
        Arrays.sort(expected);
        assertArrayEquals(expected, array);
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