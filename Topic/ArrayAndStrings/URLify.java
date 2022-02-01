package Topic.ArrayAndStrings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/** 
 * Write a method to replace all spaces in a string with '%20'.
 * You may assume that the string has sufficient space at the end to hold the additional characters,
 * and that you are given the "true" length of the string.
 * (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
 */

public class URLify {
    public static String urlify(char[] str, int n){
        int cnt = 0;
        for (int i = 0; i < n; i++)
            if (str[i] == ' ')
                cnt++;
        int len = n + cnt * 2 - 1;
        if (len < str.length)
            str[len] = '\0';
        for (int i = n - 1; i >= 0; i--){
            if (str[i] == ' '){
                str[len--] = '0';
                str[len--] = '2';
                str[len--] = '%';
                if(--cnt == 0) break;
            } else {
                str[len--] = str[i];
            }
        }
        return new String(str);
    }

    /** Use StringBuilder */
    public static String urlify2(String str, int n){
        StringBuilder sb = new StringBuilder();
        String space = "%20";
        for (int i = 0; i < n; i++){
            if (str.charAt(i) == ' ')
                sb.append(space);
            else
                sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    @Test
    public void test1(){
        char[] str = "Mr John Smith    ".toCharArray();
        int n = 13;
        String expected = "Mr%20John%20Smith";
        String actual = urlify(str, n);
        assertEquals(expected, actual);
    }

    @Test
    public void test2(){
        String str = "Mr John Smith    ";
        int n = 13;
        String expected = "Mr%20John%20Smith";
        String actual = urlify2(str, n);
        assertEquals(expected, actual);
    }
}