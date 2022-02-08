package Topic.LinkedList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import DioUtility.Generic.ListNode;

/**
 * Implement an algorithm to delete a node in the middle
 * (i.e., any node but the first and last node, not necessarily the exact middle) of a singly linked list,
 * given only access to that node.
 * 
 * Example: a -> b -> c -> d -> e -> f
 *   After: a -> b -> c-------> e -> f
 * Example: a -> b -> c -> d -> e -> f -> g
 *   After: a -> b -> c-------> e -> f -> g
 */

public class DeleteMiddleNode {
    public static void deleteMiddleNode(ListNode<Integer> node){
        // if list's length < 3 then return
        // when num of length is even remove the len/2 th node
        // when num of length is odd remove the (len+1)/2 th node
        if (node.next == null || node.next.next == null)
            return;
        ListNode<Integer> slow = node;
        ListNode<Integer> fast = node.next;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
    }

    @Test
    public void test1(){
        ListNode<Integer> node = new ListNode<>(1,2,3,4,5,6);
        deleteMiddleNode(node);
        Object[] expected = new Object[]{1,2,3,5,6};
        Object[] actual = node.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void test2(){
        ListNode<Integer> node = new ListNode<>(1,2,3,4,5,6,7);
        deleteMiddleNode(node);
        Object[] expected = new Object[]{1,2,3,5,6,7};
        Object[] actual = node.toArray();
        assertArrayEquals(expected, actual);
    }
}
