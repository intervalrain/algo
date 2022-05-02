package src.DioUtility.Topic.ArrayAndStrings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * Assume you have a method isSubstring which checks if one word is a substring of another.
 * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring.
 * (e.g., "waferbottle" is a rotation of "elttobrefaw".)
 */

public class StringRotation{

    public boolean isRotation(String s1, String s2){
        int len = s1.length();
        if (len == s2.length() && len > 0){
            String s1s1 = s1 + s1;
            return isSubstring(s1s1, s2);
        }
        return false;
    }

    public boolean isSubstring(String s1, String s2){
        for (int i = 0; i + s2.length() < s1.length(); i++){
            if (s1.charAt(i) != s2.charAt(0)){
                continue;
            } else if (s1.substring(i, i + s2.length()).equals(s2)){
                return true;
            }
        }
        return false;
    }

    @Test
    public void test1(){
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        boolean expected = true;
        boolean actual = isRotation(s1, s2);
        assertEquals(expected, actual);
    }

    @Test
    public void test2(){
        String s1 = "water";
        String s2 = "retaw";
        boolean expected = false;
        boolean actual = isRotation(s1, s2);
        assertEquals(expected, actual);
    }
}
