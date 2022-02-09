package Topic.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import DioUtility.Generic.ListNode;

/**
 * Given a linked list which might contain a loop,
 * implement an algorithm that returns the nodes at the beginning of the loop (if one exists)
 */


 // A ---> B -> C
 // A ---> B -> C -> B -> C
 // 2(a + b) = a + b + c + b
 // a = c
 // find a.
public class LoopDetection {
    public static ListNode<Character> loopDetect(ListNode<Character> node){
        ListNode<Character> fast = node;
        ListNode<Character> slow = node;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                slow = node;
                while (slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    @Test
    public void test1(){
        ListNode<Character> node = new ListNode<>('A', 'B', 'C', 'D', 'E');
        node.next.next.next.next.next = node.next.next;  // C is loop start.
        ListNode<Character> expected = node.next.next;
        ListNode<Character> actual = loopDetect(node);
        assertEquals(expected, actual);
    }
}
