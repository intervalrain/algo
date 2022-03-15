package DioUtility.Generic.MapUtil;

import java.util.Objects;
import java.lang.Object;

public class Node<K,V>{
    final int hash;
    final K key;
    V value;
    Node<K,V> next;

    Node(int hash, K key, V value, Node<K,V> next){
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public K getKey()        { return key; }
    public V getValue()      { return value; }
    public String toString() { return key + ":" + value; }

    public final V setValue(V value) {
        V oldValue = value;
        this.value = value;
        return oldValue;
    }

    public final boolean equals(Object o){
        if (o == this)
            return true;
        if (o instanceof Node){
            Node<?,?> e = (Node<?,?>)o;
            if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue()))
                return true;
        }
        return false;
    }
}