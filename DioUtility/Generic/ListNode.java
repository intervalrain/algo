package DioUtility.Generic;

import java.util.Arrays;

public class ListNode<T>{
    
    public T val;
    public ListNode<T> next;

    public ListNode(T val, ListNode<T> next){
        this.val = val;
        this.next = next;
    }
    public ListNode(T val) {this(val , null);}
    public ListNode()      {this(null, null);}

    @SafeVarargs
    public ListNode(T... array){
        this(array[0]);
        ListNode<T> curr = new ListNode<>(array[array.length - 1]);
        for (int i = array.length - 2; i > 0; i--){
            ListNode<T> prev = new ListNode<>(array[i], curr);
            curr = prev;
        }
        this.next = curr;
    }

    public boolean hasNext(){
        return next != null;
    }

    private boolean cycleFound(){
        ListNode<T> fast = this;
        ListNode<T> slow = this;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }

    public int size(){
        if(cycleFound())
            return -1;
        ListNode<T> curr = this;
        int cnt = 0;
        while (curr != null){
            curr = curr.next;
            cnt++;
        }
        return cnt;
    }

    public Object[] toArray(){
        if (this.cycleFound())
            return null;
        ListNode<T> node = this;
        Object[] array = new Object[this.size()];
        toArraySub(node, array);
        return array;
    }

    private void toArraySub(ListNode<T> node, Object[] array){
        if (node == null) return;
        int index = 0;
        while (node != null){
            array[index++] = node.val;
            node = node.next;
        }
    }

    public String toString(){
        if(cycleFound())
            return "Cycled List";
        return Arrays.toString(this.toArray());
    }
}
