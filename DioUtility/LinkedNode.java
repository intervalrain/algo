package DioUtility;

import java.util.Arrays;

public class LinkedNode{
    public int val;
    public LinkedNode next;

    public LinkedNode(int val, LinkedNode next) {this.val = val; this.next = next;}
    public LinkedNode(int val)                {this.val = val; this.next = null;}
    public LinkedNode()                       {this.val = 0  ; this.next = null;}
    public LinkedNode(int... num){
        this.val = num[0];
        LinkedNode nextNode = new LinkedNode(num[num.length-1]);
        for (int i = num.length - 2; i > 0; i--){
            LinkedNode currNode = new LinkedNode(num[i], nextNode);
            nextNode = currNode;
        }
        this.next = nextNode;
    }

    int size(){
        int cnt = 0;
        LinkedNode curr = this;
        while (curr != null){
            curr = curr.next;
            cnt++;
        }
        return cnt;
    }

    int[] toArray(){
        int n = this.size();
        int[] array = new int[n];
        LinkedNode curr = this;
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
