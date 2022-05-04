package src.Topic.ArrayAndStrings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

/** Given two strings, write a method to decide if one is a permutation of the other. */

public class CheckPermutation {
    /** Use bitmask as dp map but pattern cannot be repeateable alphabet*/
    public static boolean checkPermutation(String toMatch, String pattern){
        if (toMatch.length() != pattern.length())
            return false;
        int mask = 0;
        for (int i = 0; i < pattern.length(); i++){
            mask |= (1 << (pattern.charAt(i) - 'a'));
        }
        for (int i = 0; i < toMatch.length(); i++){
            int test = (mask & (1 << (toMatch.charAt(i) - 'a')));
            mask -= test;
            if (test == 0) return false;
        }
        return true;
    }
    /** Use array as dp map */
    public static boolean checkPermutation2(String toMatch, String pattern){
        int[] dp = new int[128];
        for (int i = 0; i < pattern.length(); i++)
            dp[pattern.charAt(i)]++;
        for (int i = 0; i < toMatch.length(); i++){
            dp[toMatch.charAt(i)]--;
            if (dp[toMatch.charAt(i)] < 0)
                return false;
        }
        return true;
    }

    @Test
    public void test(){
        String pattern = "abcd";
        String str1 = "dabc";
        String str2 = "abec";
        String str3 = "cccc";
        assertTrue(checkPermutation(str1, pattern));
        assertFalse(checkPermutation(str2, pattern));
        assertFalse(checkPermutation(str3, pattern));
    }
}
