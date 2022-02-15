package Topic.Sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

/**
 * Bucket Sort:
 * 
 * Bucket sort is mainly useful when input is uniformly distributed over a range.
 * 
 * Best Time Complexity: O(n+k)
 * Avg Time Complexity: O(n+k)
 * Worst Time Complexity: O(n^2)   -> if data is non-uniformly distributed
 * Space Complexity: O(n)
 */

public class BucketSort {
    
    public static void sort(float[] array){
        int n = array.length;
        @SuppressWarnings("unchecked")
        List<Float>[] buckets = new ArrayList[n];

        // 1. create n empty buckets
        for (int i = 0; i < n; i++){
            buckets[i] = new ArrayList<Float>();
        }

        // 2. put array elements in different buckets
        for (int i = 0; i < n; i++){
            float idx = array[i] * n;    // idx = array * len / (max - min)  max = 1.0, min = 0.0
            buckets[(int)idx].add(array[i]);
        }

        // 3. sort individual buckets
        for (int i = 0; i < n; i++){
            insertionSort(buckets[i]);
        }

        // 4. concatenate all buckets into array
        int index = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < buckets[i].size(); j++){
                array[index++] = buckets[i].get(j);
            }
        }
    }

    public static void insertionSort(List<Float> buckets){
        int i, j;
        for (i = 1; i < buckets.size(); i++){
            float curr = buckets.get(i);
            for (j = i - 1; j >= 0; j--){
                if (buckets.get(j) < curr){
                    break;
                }
            }
            buckets.add(j + 1, buckets.remove(i));
        }
    }
 
    @Test
    public void floatingTest(){
        float[] array    = new float[]{0.78f, 0.17f, 0.39f, 0.26f, 0.72f, 0.94f, 0.21f, 0.12f, 0.23f, 0.68f};
        sort(array);
        float[] expected = new float[]{0.12f, 0.17f, 0.21f, 0.23f, 0.26f, 0.39f, 0.68f, 0.72f, 0.78f, 0.94f};
        assertArrayEquals(expected, array);
    }
}