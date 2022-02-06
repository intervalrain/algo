// package Topic.Sorting;

// import static org.junit.Assert.assertArrayEquals;

// import java.util.Arrays;

// import org.junit.Test;

// import DioUtility.arrayGenerator;
 
// /**
//  * Radix Sort
//  * 
//  * Sort input array using counting sort(or any other stable sort) according to the i'th digit.
//  * Do counting sort for every digit.
//  * exp is passed instead of passing digit number. (exp is 10^i where i is current digit number.)
//  * 
//  * Best Time Complexity: O(nk)
//  * Avg Time Complexity: O(nk)
//  * Worst Time Complexity: O(nk)
//  * Space Complexity: O(n+k)
//  */
// public class RadixSort {

//     public static void sort(int array[]){
//         int n = array.length;
//         int m = getMax(array);
        
//         for (int exp = 1; m / exp > 0; exp *= 10)
//             countSort(array, n, exp);
//     }

//     static int getMax(int array[]){
//         int max = array[0];
//         for (int i = 1; i < array.length; i++)
//             max = Math.max(array[i], max);
//         return max;
//     }
 
//     // A function to do counting sort of arr[] according to the digit represented by exp.
//     static void countSort(int[] array, int exp){
//         int n = array.length;
//         int output[] = new int[n];
//         int count[] = new int[10];
 
//         // Store count of occurrences in count[]
//         for (int i = 0; i < n; i++)
//             count[(array[i] / exp) % 10]++;
 
//         // Change count[i] so that count[i] now contains
//         // actual position of this digit in output[]
//         for (i = 1; i < 10; i++)
//             count[i] += count[i - 1];
 
//         // Build the output array
//         for (i = n - 1; i >= 0; i--) {
//             output[count[(arr[i] / exp) % 10] - 1] = arr[i];
//             count[(arr[i] / exp) % 10]--;
//         }
 
//         // Copy the output array to arr[], so that arr[] now
//         // contains sorted numbers according to current digit
//         for (i = 0; i < n; i++)
//             arr[i] = output[i];
//     }
 
//     @Test
//     public void test1(){
//         int[] array = new int[]{3,5,7,8,4,2,1,9,6};
//         sort(array);
//         int[] expected = new int[]{1,2,3,4,5,6,7,8,9};
//         assertArrayEquals(expected, array);
//     }

//     @Test
//     public void test2(){
//         int[] array = new int[]{9,8,7,6,5,4,3,2,1};
//         sort(array);
//         int[] expected = new int[]{1,2,3,4,5,6,7,8,9};
//         assertArrayEquals(expected, array);
//     }

 
//     @Test
//     public void randomTest(){
//         int[] array    = new arrayGenerator(128, false).toArray();
//         sort(array);
//         int[] expected = new arrayGenerator(128, true).toArray();
//         assertArrayEquals(expected, array);
//     }
// }