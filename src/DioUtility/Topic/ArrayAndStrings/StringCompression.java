package src.DioUtility.Topic.ArrayAndStrings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * Implement a method to perform basic string compression using the counts of repeated characters.
 * For example, the string aabcccccaaa would be become a2b1c5a3.
 * If the "compressed" string would not become smaller than the original string,
 * your method should return the original string.
 * You can assume the string has only uppercase and lowercase letters(a-z).
 */

public class StringCompression{
    public String compress(String str){
        char curr = str.charAt(0);
        int cnt = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length(); i++){
            if (str.charAt(i) != curr){
                sb.append(curr);
                sb.append(cnt);
                cnt = 1;
                curr = str.charAt(i);
            } else {
                cnt++;
            }
        }
        sb.append(curr);
        sb.append(cnt);
        return str.length() > sb.length() ? sb.toString() : str;
    }

    @Test
    public void test1(){
        String str = "aabcccccaaa";
        String expected = "a2b1c5a3";
        String actual = compress(str);
        assertEquals(expected, actual);
    }

    @Test
    public void test2(){
        String str = "abcde";
        String expected = "abcde";
        String actual = compress(str);
        assertEquals(expected, actual);
    }
}
