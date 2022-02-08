package DioUtility.DioInt;

import java.util.Arrays;

public class ListNode{
    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    public ListNode(int val)                {this.val = val; this.next = null;}
    public ListNode()                       {this.val = 0  ; this.next = null;}
    public ListNode(int... num){
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
