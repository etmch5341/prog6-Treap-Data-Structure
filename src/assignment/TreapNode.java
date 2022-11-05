package assignment;

public class TreapNode<K, V> {
    public K key;
    public int priority;
    public V value;
    public TreapNode left;
    public TreapNode right;
    public TreapNode parent;

    public TreapNode(K key, V value, TreapNode parent){
        this.key = key;
        this.value = value;
        priority = (int)(Math.random()*Treap.MAX_PRIORITY);
        this.parent = parent;
    }

    //TEST CONSTRUCTOR
    //TODO: REMOVE ONCE DONE
    public TreapNode(K key, V value, TreapNode parent, int priority){
        this.key = key;
        this.value = value;
        this.priority = priority;
        this.parent = parent;
    }

    public V getValue(){
        return value;
    }
}
