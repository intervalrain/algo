package Topic.LinkedList;

import DioUtility.ListNode;
import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

/**
 * Write code to remove duplicates from an unsortee linked list.
 */

public class RemoveDups{
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

    @Test
    public void test1(){
        ListNode<Integer> head = new ListNode<>(5,3,9,9,7,2,6,1,3,5,2,0);
        deleteDups(head);
        System.out.println(head);
        Object[] expected = new Integer[]{5,3,9,7,2,6,1,0};
        Object[] actual = head.toArray();
        assertArrayEquals(expected, actual);
    }
}

