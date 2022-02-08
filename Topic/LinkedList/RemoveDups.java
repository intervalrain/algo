package Topic.LinkedList;

import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

import DioUtility.Generic.ListNode;

/**
 * Write code to remove duplicates from an unsortee linked list.
 */

public class RemoveDups{
    /** Use temporary buffer to improve time complexity: O(N) */
    public void deleteDups(ListNode<Integer> node){
        Set<Integer> map = new HashSet<>();
        ListNode<Integer> curr = node;
        map.add(curr.val);
        while (curr.hasNext()){
            if (!map.contains(curr.next.val))
                map.add(curr.next.val);
            else {
                while (map.contains(curr.next.val))
                    curr.next = curr.next.next;
            }
            curr = curr.next;
        }

    }

    /** Use no extra space memory to solve the problem. */
    public void removeDups(ListNode<Integer> node){
        ListNode<Integer> curr = node;
        while (curr != null){
            ListNode<Integer> runner = curr;
            while (runner.next != null){
                if (runner.next.val == curr.val){
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            curr = curr.next;
        }   
    }

    @Test
    public void test1(){
        ListNode<Integer> head = new ListNode<>(5,3,9,9,7,2,6,1,3,5,2,0);
        deleteDups(head);
        System.out.println(head);
        Object[] expected = new Integer[]{5,3,9,7,2,6,1,0};
        Object[] actual = head.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void test2(){
        ListNode<Integer> head = new ListNode<>(5,3,9,9,7,2,6,1,3,5,2,0);
        removeDups(head);
        System.out.println(head);
        Object[] expected = new Integer[]{5,3,9,7,2,6,1,0};
        Object[] actual = head.toArray();
        assertArrayEquals(expected, actual);
    }
}

