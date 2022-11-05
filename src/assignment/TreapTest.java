package assignment;

public class TreapTest<K, V> extends TreapMap{

    /**
     * Constructor: creates a new root node
     *
     * @param key
     * @param value
     */
    public TreapTest(K key, V value) {
        super((Comparable) key, value);
    }
}
