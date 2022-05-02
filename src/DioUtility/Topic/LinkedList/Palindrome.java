package src.DioUtility.Topic.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

import src.DioUtility.Generic.ListNode;
import src.DioUtility.Generic.Stack;

/**
 * Implement a function to check if a linked list is a palindrome.
 */

public class Palindrome {
    /** Method 1: Create a reverse list. */
    public static boolean isPalindrome(ListNode<Character> node){
        return reverseNode(node).toString().equals(node.toString());
    }
    public static ListNode<Character> reverseNode(ListNode<Character> node){
        ListNode<Character> head = null;
        while (node != null){
            ListNode<Character> n = new ListNode<>(node.val);
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
    }

    /** Method 2: Solve by iteration */
    public static boolean isPalindrome2(ListNode<Character> node){
        ListNode<Character> fast = node;
        ListNode<Character> slow = node;
        Stack<Character> stack = new Stack<>();
        while (fast != null && fast.next != null){
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        // odd items, ignore middle item.
        if (fast != null)
            slow = slow.next;
        while (slow != null){
            if (stack.pop() != slow.val){
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    /** Method 3: Solve by recursion */
    private static class Result{
        public ListNode<Character> node;
        public boolean result;
        public Result(ListNode<Character> node, boolean result){
            this.result = result;
            this.node = node;
        }
    }

    public static boolean isPalindrome3(ListNode<Character> node){
        int len = lengthOfList(node);
        Result p = isPalindromeRecursive(node, len);
        return p.result;
    }

    public static Result isPalindromeRecursive(ListNode<Character> node, int len){
        if (node == null || len <= 0){     // even
            return new Result(node, true);
        } else if (len == 1)               // odd, ignore mid item
            return new Result(node.next, true);

        Result res = isPalindromeRecursive(node.next, len - 2);
        if (!res.result || res.node == null){
            return res;
        }
        res.result = (node.val == res.node.val);
        res.node = res.node.next;
        return res;
    }

    private static int lengthOfList(ListNode<Character> node){
        int cnt = 0;
        while (node != null){
            cnt++;
            node = node.next;
        }
        return cnt;

    }


    @Test
    public void test(){
        ListNode<Character> node1 = new ListNode<>('a', 'b', 'c', 'c', 'b', 'a');
        ListNode<Character> node2 = new ListNode<>('a', 'b', 'c', 'b', 'a');
        ListNode<Character> node3 = new ListNode<>('a', 'b', 'd', 'c');
        ListNode<Character> node4 = new ListNode<>('a', 'b', 'c', 'b', 'c', 'a');
        ListNode<Character> node5 = new ListNode<>('a', 'b', 'c', 'b', 'c', 'b', 'a');
        assertTrue(isPalindrome(node1));
        assertTrue(isPalindrome(node2));
        assertFalse(isPalindrome(node3));
        assertFalse(isPalindrome(node4));
        assertTrue(isPalindrome(node5));
    }

    @Test
    public void test2(){
        ListNode<Character> node1 = new ListNode<>('a', 'b', 'c', 'c', 'b', 'a');
        ListNode<Character> node2 = new ListNode<>('a', 'b', 'c', 'b', 'a');
        ListNode<Character> node3 = new ListNode<>('a', 'b', 'd', 'c');
        ListNode<Character> node4 = new ListNode<>('a', 'b', 'c', 'b', 'c', 'a');
        ListNode<Character> node5 = new ListNode<>('a', 'b', 'c', 'b', 'c', 'b', 'a');
        assertTrue(isPalindrome2(node1));
        assertTrue(isPalindrome2(node2));
        assertFalse(isPalindrome2(node3));
        assertFalse(isPalindrome2(node4));
        assertTrue(isPalindrome2(node5));
    }

    @Test
    public void test3(){
        ListNode<Character> node1 = new ListNode<>('a', 'b', 'c', 'c', 'b', 'a');
        ListNode<Character> node2 = new ListNode<>('a', 'b', 'c', 'b', 'a');
        ListNode<Character> node3 = new ListNode<>('a', 'b', 'd', 'c');
        ListNode<Character> node4 = new ListNode<>('a', 'b', 'c', 'b', 'c', 'a');
        ListNode<Character> node5 = new ListNode<>('a', 'b', 'c', 'b', 'c', 'b', 'a');
        assertTrue(isPalindrome3(node1));
        assertTrue(isPalindrome3(node2));
        assertFalse(isPalindrome3(node3));
        assertFalse(isPalindrome3(node4));
        assertTrue(isPalindrome3(node5));
    }
}
