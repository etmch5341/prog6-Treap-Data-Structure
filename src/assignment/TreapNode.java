package assignment;

public class TreapNode<K, V> {
    public K key;
    public int priority;
    public V value;
    public TreapNode left;
    public TreapNode right;
    public TreapNode parent;

    //Create new TreapNode with key, value, and parent
    public TreapNode(K key, V value, TreapNode parent){
        this.key = key;
        this.value = value;
        priority = (int)(Math.random()*Treap.MAX_PRIORITY);
        this.parent = parent;
    }

    //Create new TreapNode with a set key, value, parent, and priority
    public TreapNode(K key, V value, TreapNode parent, int priority){
        this.key = key;
        this.value = value;
        this.priority = priority;
        this.parent = parent;
    }

    //Return value of the TreapNode
    public V getValue(){
        return value;
    }
}
