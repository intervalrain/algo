package src.DioUtility.Topic.ArrayAndStrings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.Test;

/**
 * There area three types of edits that can be performed on strings:
 * 1. insert a character
 * 2. remove a character
 * 3. replace a character
 * Given two strings, write a function to check if they are one edit (for zero edits) away.
 */

public class OneAway{
    public static boolean isOneAway(String s1, String s2){
        int bitmask1 = 0;
        int bitmask2 = 0;
        int map1[] = new int[26];
        int map2[] = new int[26];
        for (int i = 0; i < s1.length(); i++){
            bitmask1 |= (1 << (s1.charAt(i) - 'a'));
            map1[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++){
            bitmask2 |= (1 << (s2.charAt(i) - 'a'));
            map2[s2.charAt(i) - 'a']++;
        }
        int bitmask = bitmask1 ^ bitmask2;
        if (Integer.bitCount(bitmask) <= 1)
            return true;
        if (Integer.bitCount(bitmask) == 2){
            int cnt1 = 0, cnt2 = 0;
            int bit1 = bitmask1 & bitmask;
            int bit2 = bitmask2 & bitmask;
            while(bit1 > 1) {
                bit1 >>>= 1;
                cnt1++;
            }
            while(bit2 > 1){
                bit2 >>>= 1;
                cnt2++;
            }
            if (map1[cnt1] == map2[cnt2]) return true;
        }
        return false;
    }

    @Test
    public void test1(){
        assertTrue(isOneAway("pale", "ple"));
        assertTrue(isOneAway("pales", "pale"));
        assertTrue(isOneAway("pale", "bale"));
        assertFalse(isOneAway("pale", "bake"));
    }
    @Test
    public void test2(){
        assertTrue(oneEditAway("pale", "ple"));
        assertTrue(oneEditAway("pales", "pale"));
        assertTrue(oneEditAway("pale", "bale"));
        assertFalse(oneEditAway("pale", "bake"));
    }
    @Test
    public void test3(){
        assertTrue(oneEditAway2("pale", "ple"));
        assertTrue(oneEditAway2("pales", "pale"));
        assertTrue(oneEditAway2("pale", "bale"));
        assertFalse(oneEditAway2("pale", "bake"));   
    }

    /** Reference Solution 1 */
    public static boolean oneEditAway(String s1, String s2){
        if (s1.length() == s2.length())
            return OneEditReplace(s1, s2);
        else if (s1.length() - s2.length() == 1)
            return OneEditInsert(s1, s2);
        else if (s1.length() - s2.length() == -1)
            return OneEditInsert(s2, s1);
        return false;
    }
    private static boolean OneEditInsert(String s1, String s2){
        int idx1 = 0, idx2 = 0 ;
        while (idx1 < s1.length() && idx2 < s2.length()){
            if (s1.charAt(idx1) != s2.charAt(idx2)){
                if (idx1 != idx2)
                    return false;
                idx1++;
            } else {
                idx1++;
                idx2++;
            }
        }
        return true;
    } 
    private static boolean OneEditReplace(String s1, String s2){
        boolean Found = false;
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) != s2.charAt(i)){
                if (Found) return false;
                Found = true;
            }
        }
        return true;
    }

    /** Reference Solution 2 */
    public static boolean oneEditAway2(String s1, String s2){
        int diff;
        if (Math.abs(diff = s1.length() - s2.length()) > 1)
            return false;
        if (diff > 0)
            return oneEditAway2(s2, s1);
        int idx1 = 0, idx2 = 0;
        boolean Found = false;
        while (idx1 < s1.length() && idx2 < s2.length()){
            if (s1.charAt(idx1) != s2.charAt(idx2)){
                if (Found) return false;
                Found = true;
                if (diff != 0)
                    idx2++;
            }
            idx1++;
            idx2++;
        }
        return true;
    }
}
