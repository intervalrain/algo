package src.DioUtility.Topic.LinkedList;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import src.DioUtility.DioInt.ListNode;

/**
 * Write code to partition a linked list around a value x,
 * such that all nodes less than x come before all nodes greater than or equal to x.
 * (IMPORTANT: The partition element x can appear anywhere in the "right partition";
 * it does not need to appear between the left and right partitions.
 * The additional spacing in the example below indicates the partition.
 * Yes, the output below is one of many valid outputs!)
 */

public class Partition {
    public static ListNode partition(ListNode node, int delimeter){
        ListNode bigger = new ListNode();
        ListNode smaller = new ListNode();
        ListNode b = bigger;
        ListNode s = smaller;
        while (node != null){
            if (node.val >= delimeter){
                b.next = node;
                b = b.next;
            } else {
                s.next = node;
                s = s.next;
            }
            node = node.next;
        }
        b.next = null;
        s.next = bigger.next;
        return smaller.next;
    }

    @Test
    public void test(){
        ListNode node = new ListNode(3,5,8,5,10,2,1);
        int delimeter = 5;
        ListNode actual = partition(node, delimeter);
        System.out.println(actual.toString());
        ListNode curr = node;
        Map<Integer, Integer> map = new HashMap<>();
        while (curr != null){
            if (curr.val < delimeter){
                map.put(curr.val, map.getOrDefault(curr.val, 0) + 1);
            }
            curr = curr.next;
        }
        while (actual != null){
            if (actual.val >= delimeter)
                break;
            map.put(actual.val, map.getOrDefault(actual.val, 0) - 1);
            actual = actual.next;
        }
        for (Integer key : map.keySet()){
            assertTrue(map.get(key) == 0);
        }

    }

}
