package src.Topic.ArrayAndStrings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

/**
 * Given a string, write a function to chcek if it is a permutation of a palindrome.
 * A palindrome is a word or phrase that is the same forwards and backwards.
 * A permutation is a rearrangement of letters.
 * The palindrome does not need to be limited to just dictionary words.
 * You can ignore casting and non-letter characters.
 */

public class PalindromePermutation{
    public static boolean isPalindromePermutation(String str){
        int[] map = new int[26];
        for (int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) ch += 32;
            if (ch == ' ') continue;
            map[ch - 'a']++;
        }
        int odds = 0;
        for (int i = 0; i < 26; i++){
            if (map[i] % 2 == 1) odds++;
        }
        return !(odds >= 2);
    }

    @Test
    public void test(){
        String str1 = "tact Coa";
        assertTrue(isPalindromePermutation(str1));
    }
}
