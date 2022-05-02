package src.DioUtility.Topic.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import src.DioUtility.DioInt.ListNode;

/**
 * Given two (singly) linked lists, determine if the two lists intersect.
 * Return the intersecting node.
 * Note that the intersection is defined based on reference, not value.
 * That is, if the kth node of the first linked list is the exact same node (by reference) as the jth node of the second linked list,
 * then they are intersecting.
 */

public class Intersection {
    
    ListNode findIntersection(ListNode list1, ListNode list2){
        int n1 = getLength(list1);
        int n2 = getLength(list2);
        if (n1 < n2)
            return findIntersection(list2, list1);
        int n = n1 - n2;
        while (n > 0){
            list1 = list1.next;
            n--;
        }
        while (list1 != null){
            if (list1 == list2)
                break;
            list1 = list1.next;
            list2 = list2.next;
        }
        return list1;
    }
    public static int getLength(ListNode node){
        ListNode curr = node;
        int cnt = 0;
        while (curr != null){
            curr = curr.next;
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args){
        ListNode node = new ListNode(3,1,5,9,7,2,1);
        System.out.println(getLength(node));
    }

    @Test
    public void test(){
        ListNode list1 = new ListNode(3,1,5,9,7,2,1);
        ListNode list2 = new ListNode(4,6);
        list2.next.next = list1.next.next.next.next;
        ListNode expected = list2.next.next;
        ListNode actual = findIntersection(list1, list2);
        assertEquals(expected, actual);
    }


}
