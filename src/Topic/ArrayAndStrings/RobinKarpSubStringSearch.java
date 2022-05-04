package src.Topic.ArrayAndStrings;

import java.util.List;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
/**
 * Searching substring in a string by using hashing.
 */

public class RobinKarpSubStringSearch{
    public static List<Integer> search(String toFind, String str){
        int n = str.length();
        int s = toFind.length();
        if (n == 0 || n < s)
            return null;
        List<Integer> res = new ArrayList<>();
        int[] dp = new int[n - s];
        dp[0] = hash(str.substring(0, s));

        int target = hash(toFind);
        if (dp[0] == target && str.substring(0, s).equals(toFind))
            res.add(0);

        for (int i = 1; i < n - s ; i++){
            dp[i] = (dp[i-1] - (str.charAt(i-1) - 'A') * (int)Math.pow(2, s-1)) * 2 + str.charAt(i+s-1) - 'A';
            if (dp[i] == target && str.substring(i, i + s).equals(toFind))
                res.add(i);
        }
        return res.size() == 0 ? null : res;
    }

    public static int hash(String str){
        int h = 0;
        for (int i = 0; i < str.length(); i++){
            h += (str.charAt(i) - 'A') * Math.pow(2, str.length() - i - 1);
        }
        return h;
    }

    @Test
    public void test1(){
        String toFind = "good";
        String str = "I have a good mood if it's a good day";
        List<Integer> expected = List.of(9,29);
        List<Integer> actual = search(toFind, str);
        assertEquals(expected, actual);
    }
}