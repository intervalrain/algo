package src.DioUtility.Topic.LinkedList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

import src.DioUtility.Generic.ListNode;

/**
 * You have two numbers represented by a linked list,
 * where each node contains a single digits.
 * The digits are stored in reverse order,
 * such that the 1's digit is the head of the list.
 * Write a function that adds two numbers and returns the sum as a linked list.
 * (You may not allowed to "cheat" and just convert the linked list to an integer.)
 */

public class SumList {
    // return with a new Linked list.
    public ListNode<Integer> add(ListNode<Integer> a, ListNode<Integer> b){
        ListNode<Integer> dummy = new ListNode<>();
        ListNode<Integer> curr = dummy;
        int carryin = 0;
        while (a != null && b != null){
            int sum = a.val + b.val + carryin;
            int rem = sum % 10;
            carryin = sum / 10;
            curr.next = new ListNode<>(rem);
            curr = curr.next;
            a = a.next;
            b = b.next;
        }
        if (a != null && b == null)
            curr.next = add(new ListNode<>(carryin), a);
        else if (b != null && a == null)
            curr.next = add(new ListNode<>(carryin), b);       
        else if (carryin != 0)
            curr.next = new ListNode<>(carryin);
        return dummy.next;
    }
    // return as a original Linked list.
    public void add2(ListNode<Integer> a, ListNode<Integer> b){
        int carryin = 0;
        while (a != null && b != null){
            a.val += b.val + carryin;
            carryin = a.val / 10;
            a.val %= 10;
            a = a.next;
            b = b.next;
        }
        if (carryin != 0){
            if (a == null)
                a = b;
            while (carryin > 0 && a != null){
                a.val += carryin;
                carryin = a.val / 10;
                a.val %= 10;
                if (a.next == null && carryin > 0)
                    a.next = new ListNode<>(0);
                a = a.next;   
            }
        }
    }


    @Test
    public void test1(){
        // 617 + 295 = 912
        ListNode<Integer> a = new ListNode<>(7,1,6);
        ListNode<Integer> b = new ListNode<>(5,9,2);
        ListNode<Integer> expected = new ListNode<>(2,1,9);
        ListNode<Integer> actual = add(a, b);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test2(){
        // 617 + 295 = 912
        ListNode<Integer> a = new ListNode<>(7,1,6);
        ListNode<Integer> b = new ListNode<>(5,9,2);
        ListNode<Integer> expected = new ListNode<>(2,1,9);
        add2(a,b);
        ListNode<Integer> actual = a;
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test3(){
        ListNode<Integer> a = new ListNode<>(9,9,9,9,9,9,9);
        ListNode<Integer> b = new ListNode<>(9,9,9,9);
        ListNode<Integer> actual = add(a,b);
        ListNode<Integer> expected = new ListNode<>(8,9,9,9,0,0,0,1);
        assertArrayEquals(actual.toArray(), expected.toArray());
        add2(a,b);
        assertArrayEquals(a.toArray(), expected.toArray());
    }
    
}
