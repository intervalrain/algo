package Topic.LinkedList;

import DioUtility.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

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
}
