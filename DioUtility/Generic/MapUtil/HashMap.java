package DioUtility.Generic.MapUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

public class HashMap<K,V>{  // extends AbstractMap<K,V> implements Map<K,V>, CLoneable, Serializable

    // @java.io.Serial
    // private static final long serialVersionUID = 362498820763181265L;

    /** Default Values */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // static final int TREEIFY_THRESHOLD = 8;
    // static final int UNTREEIFY_THRESHOLD = 6;
    // static final int MIN_TREEIFY_CAPACITY = 64;

    /** Utility */
    static final int hash(Object key){
        int h = key.hashCode();
        return (key == null) ? 0 : (h ^ (h >>> 16));
    }

    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                for (Type t : ts) {
                    if ((t instanceof ParameterizedType) &&
                        ((p = (ParameterizedType) t).getRawType() ==
                         Comparable.class) &&
                        (as = p.getActualTypeArguments()) != null &&
                        as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }

    @SuppressWarnings({"rawtypes","unchecked"}) // for cast to Comparable
    static int compareComparables(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 :
                ((Comparable)k).compareTo(x));
    }

    static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /** Fields */
    transient Node<K,V>[] table;
    transient Set<Map.Entry<K,V>> EntrySet;
    int size;
    int modCount;
    int threshold;
    final float loadFactor;
    
    /** Constructor */
    public HashMap(int initialCapacity, float loadFactor){
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    public HashMap(int initialCapacity){
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(){
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashMap(Map<? extends K, ? extends V> m){
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }

    /** Operations */
    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict){
        int s = m.size();
        if (s > 0){
            if (table == null){
                float ft = ((float) s/loadFactor) + 1.0F;
                int t = ((ft < (float) MAXIMUM_CAPACITY) ? (int) ft : MAXIMUM_CAPACITY);
                if (t > threshold)
                    threshold = tableSizeFor(t);
            } else {
                while (s > threshold && table.length < MAXIMUM_CAPACITY){
                    resize();
                }
            }

            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()){
                K key = e.getKey();
                V value = e.getValue();
                putVal(hash(key), key, value, false, evict);
            }
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public V get(Object key){
        Node<K,V> e = getNode(key);
        return e == null ? null : e.value;
    }

    final Node<K,V> getNode(Object key){
        Node<K,V>[] tab = table;
        int n = tab == null ? 0 : tab.length;
        int hash = hash(key);
        int ix = hash & (n-1);
        Node<K,V> first = n > 0 ? tab[ix] : null;
        K k;
        if (tab != null && n > 0 && first != null){
            k = first.key;
            if (first.hash == hash && (key == k || (key != null && key.equals(k))))
                return first;
        }
        Node<K,V> e = first.next;
        if (e != null){
            // if (first instanceof TreeNode)
            //     return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            k = e.key;
            do {
                if (e.hash == hash && (key == k || (key != null && key.equals(k)))) 
                    return e;
            } while ((e = e.next) != null);
        }
        return null;
    }

    public boolean containsKey(Object key){
        return getNode(key) != null;
    }

    public V put (K key, V value){
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict){
        Node<K,V>[] tab = table == null ? resize() : table;
        int n = tab.length;
        int h = hash(key);
        int ix = h & (n-1);
        Node<K,V> first = tab[ix];
        if (first == null)
            first = new Node<K,V>(hash, key, value, null);
        else {
            Node<K,V> e = first.next;
            K k = first.key;
            if (first.hash == hash && (key == k || (key != null && key.equals(k))))
                e = first;
            // else if (first instanceof TreeNode)
            //     e = ((TreeNode<K,V>p).putMapEntries(this, tab, hash, key, value);
            else {
                // for (int binCount = 0; ; ++binCount){
                while (true) {
                    if (e == null){
                        first.next = new Node<K,V>(hash, key, value, null);
                        // if (binCount >= TREEIFY_THRESHOLD - 1)
                        //     treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    first = e;
                }
            }
            if (e != null){
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                // afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        // afterNodeInsertion(evict);
        return null;
    }

    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    // else if (e instanceof TreeNode)
                    //     ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    // final void treeifyBin(Node<K,V>[] tab, int hash) {
    //     int n, index; Node<K,V> e;
    //     if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
    //         resize();
    //     else if ((e = tab[index = (n - 1) & hash]) != null) {
    //         TreeNode<K,V> hd = null, tl = null;
    //         do {
    //             TreeNode<K,V> p = replacementTreeNode(e, null);
    //             if (tl == null)
    //                 hd = p;
    //             else {
    //                 p.prev = tl;
    //                 tl.next = p;
    //             }
    //             tl = p;
    //         } while ((e = e.next) != null);
    //         if ((tab[index] = hd) != null)
    //             hd.treeify(tab);
    //     }
    // }

    public void putAll(Map<? extends K, ? extends V> m) {
        putMapEntries(m, true);
    }

    public V remove(Object key) {
        Node<K,V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ? null : e.value;
    }

    final Node<K,V> removeNode(int hash, Object key, Object value,
                               boolean matchValue, boolean movable) {
        Node<K,V>[] tab; Node<K,V> p; int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (p = tab[index = (n - 1) & hash]) != null) {
            Node<K,V> node = null, e; K k; V v;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;
            else if ((e = p.next) != null) {
                // if (p instanceof TreeNode)
                //     node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
                // else {
                    do {
                        if (e.hash == hash &&
                            ((k = e.key) == key ||
                             (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                        p = e;
                    } while ((e = e.next) != null);
                // }
            }
            if (node != null && (!matchValue || (v = node.value) == value ||
                                 (value != null && value.equals(v)))) {
                // if (node instanceof TreeNode)
                //     ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
                // else if (node == p)
                if (node == p)
                    tab[index] = node.next;
                else
                    p.next = node.next;
                ++modCount;
                --size;
                // afterNodeRemoval(node);
                return node;
            }
        }
        return null;
    }

    public void clear() {
        Node<K,V>[] tab;
        modCount++;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }

    public boolean containsValue(Object value) {
        Node<K,V>[] tab; V v;
        if ((tab = table) != null && size > 0) {
            for (Node<K,V> e : tab) {
                for (; e != null; e = e.next) {
                    if ((v = e.value) == value ||
                        (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;
    }
}

    