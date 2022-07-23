package src.Topic.ArrayAndStrings;

import java.util.Arrays;

public class Permutation {



    public static void nextPermutation(int[] nums){
        int n = nums.length;
        for (int left = n - 2; left >= 0; left--){
            for (int right = n - 1; right > left; right--){
                if (nums[left] < nums[right]){
                    swap(nums, left, right);
                    reverse(nums, left+1);
                    return;
                }
            }
        }
        reverse(nums);
    }
    static void swap(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    static void reverse(int[] nums){
        reverse(nums, 0, nums.length-1);
    }
    
    static void reverse(int[] nums, int a){
        reverse(nums, a, nums.length-1);
    }
    
    static void reverse(int[] nums, int a, int b){
        if (a >= b) return;
        if (a < 0 || a >= nums.length || b < 0 || b > nums.length) return;
        while (a < b){
            swap(nums, a++, b--);
        }
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4};
        for (int i = 0; i < 27; i++){
            System.out.println(Arrays.toString(nums));
            nextPermutation(nums);
        }
        
    }
}
