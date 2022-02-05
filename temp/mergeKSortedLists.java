package temp;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

import temp.PQDriver.ListNode;

public class mergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists){
        if (lists == null || lists.length == 0)
            return null;
        int n = lists.length;
        while (n > 1){
            int interval = (n + 1)/2;
            for (int i = 0; i < n/2; i++){
                lists[i] = merge(lists[i], lists[i + interval]);
            }
            n = interval;
        }
        
        return lists[0];
    }

    private static ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                curr.next = l1;
                curr = curr.next;
                l1 = l1.next;
            } else {
                curr.next = l2;
                curr = curr.next;
                l2 = l2.next;
            }
        }
        if (l1 == null){
            curr.next = l2;
        } else {
            curr.next = l1;
        }
        return dummy.next;
    }

    @Test
    public void test1(){
        ListNode[] lists = new ListNode[]{new ListNode(1,4,5), new ListNode(1,3,4), new ListNode(2,6)};
        ListNode expected = new ListNode(1,1,2,3,4,4,5,6);
        ListNode actual = mergeKLists(lists);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
