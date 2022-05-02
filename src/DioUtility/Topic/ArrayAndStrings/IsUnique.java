package src.DioUtility.Topic.ArrayAndStrings;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use asdditional data structrue?
 */

public class IsUnique{
    public static boolean isUnique(String str){
        boolean[] dp = new boolean[128];
        int n;
        if ((n = str.length()) > 128)
            return false;
        for (int i = 0; i < n; i++){
            int idx = str.charAt(i);
            if (dp[idx]) return false;
            dp[idx] = true;
        }
        return true;
    }

    @Test
    public void test(){
        assertTrue(isUnique("abcdefg"));
        assertFalse(isUnique("mississipi"));
    }
}
