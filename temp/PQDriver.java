package temp;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PQDriver {
    public static class ListNode{
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
        ListNode(int val)                {this.val = val; this.next = null;}
        ListNode()                       {this.val = 0  ; this.next = null;}
        ListNode(int... num){
            this.val = num[0];
            ListNode nextNode = new ListNode(num[num.length-1]);
            for (int i = num.length - 2; i > 0; i--){
                ListNode currNode = new ListNode(num[i], nextNode);
                nextNode = currNode;
            }
            this.next = nextNode;
        }

        int size(){
            int cnt = 0;
            ListNode curr = this;
            while (curr != null){
                curr = curr.next;
                cnt++;
            }
            return cnt;
        }

        int[] toArray(){
            int n = this.size();
            int[] array = new int[n];
            ListNode curr = this;
            for (int i = 0; i < n; i++){
                array[i] = curr.val;
                curr = curr.next;
            }
            return array;
        }

        @Override
        public String toString(){
            return Arrays.toString(this.toArray());
        }
    }

    public static void main(String[] args){
        ListNode[] lists = new ListNode[]{new ListNode(2,4,8), new ListNode(1,3,7), new ListNode(5,6,9)};
        PriorityQueue<ListNode> q = new PriorityQueue<>(lists.length, (a,b) -> a.val - b.val);
        for (ListNode list:lists){
            if (list != null)
                q.offer(list);
        }
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (!q.isEmpty()){
            curr.next = q.poll();
            curr = curr.next;
            if (curr.next != null)
                q.add(curr.next);
        }
        System.out.println(dummy.next.toString());
    }

    /**
     * Merge K Sorted Linked List:
     * 
     * k Lists, n = average counts of nodes each list;
     * 
     * (1) insert k lists: heap sort O(log k)
     * (2) poll and add into dummy linkedlist: total n*k nodes: O(nk*log(k))
     * 
     */

}
