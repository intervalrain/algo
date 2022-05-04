package src.Topic.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import src.DioUtility.Generic.ListNode;

/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 */

public class ReturnKthToLast{
    /** iterative */
    public static int printKthToLast(ListNode<Integer> head, int k){
        ListNode<Integer> curr = head;
        ListNode<Integer> runner = curr;
        while (k > 0 && runner != null){
            runner = runner.next;
            k--;
        }
        while (runner != null){
            curr = curr.next;
            runner = runner.next;
        }
        return curr.val;
    }

    @Test
    public void test1(){
        ListNode<Integer> head = new ListNode<>(7,8,6,4,5,9,1,2,3);
        int k = 3;
        int expected = 1;
        int actual = printKthToLast(head, k);
        assertEquals(expected, actual);
    }
}
