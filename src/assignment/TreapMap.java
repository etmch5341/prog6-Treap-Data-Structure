package assignment;

import java.util.Iterator;
import java.util.Stack;

public class TreapMap<K extends Comparable<K>, V> implements Treap<K, V> {
    private TreapNode root;

    /**
     * Constructor: creates a new root node
     * @param key
     * @param value
     */
    public TreapMap(K key, V value){
        root = new TreapNode(key, value, null);

        //Test: Figure 2 Treap
        root = new TreapNode(6, 1, null, 9403);
        root.left = new TreapNode(3, 2, root, 4407);
        root.right = new TreapNode(8, 3, root, 7336);
        root.left.left = new TreapNode(1, 4, root.left, 2486);
        root.left.right = new TreapNode(5, 5, root.left, 1059);
        root.right.right = new TreapNode(9, 6, root.right, 1963);
    }

    @Override
    public V lookup(K key) {
        TreapNode current = root;
        while(current != null && key.compareTo((K)current.key) != 0){
            //Key <= current
            if(key.compareTo((K)current.key) < 0){
                current = current.left;
            }
            //Key >= current
            else if(key.compareTo((K)current.key) > 0){
                current = current.right;
            }
        }
        if(current == null){
            return null;
        }
        return (V)current.getValue();
    }

    @Override
    public void insert(K key, V value) {
        TreapNode<K, V> insertNode = new TreapNode(key, value, null, 4743);
        if(root == null){
            root = insertNode;
            return;
        }

        if(lookup(key) != null){
            findNode(key).value = value;
            return;
        }

        bstInsert(root, insertNode);
        //System.out.println(insertNode.parent.key);
        while(insertNode.parent.priority < insertNode.priority){
            if(insertNode.parent.left.equals(insertNode)){
                rotateRight(insertNode.parent);
            }
            else if(insertNode.parent.right.equals(insertNode)){
                rotateLeft(insertNode.parent);
            }
        }
    }

    private TreapNode findNode(K key){
        TreapNode current = root;
        while(current != null && !current.key.equals(key)){
            //Key <= current
            if(key.compareTo((K)current.key) < 0){
                current = current.left;
            }
            //Key >= current
            else if(key.compareTo((K)current.key) > 0){
                current = current.right;
            }
        }

        return current;
    }

    private TreapNode rotateRight(TreapNode node){
        TreapNode parent = node.parent;
        TreapNode left = node.left;

        //Changing node and left's right tree reference
        node.left = left.right;
        if(left.right != null){
            left.right.parent = node;
        }
        //Changing node and left reference
        left.right = node;
        node.parent = left;
        //Chancing left and parent reference
        left.parent = parent;
        if(parent != null){
            if(parent.left.equals(node)){
                parent.left = left;
            }
            else{
                parent.right = left;
            }
        }
        //New subtree root
        return left;
    }

    private TreapNode rotateLeft(TreapNode node){
        TreapNode parent = node.parent;
        TreapNode right = node.right;

        //Changing node and right's left subtree references
        node.right = right.left;
        if(right.left != null){
            right.left.parent = node;
        }
        //Changing node and right references
        right.left = node;
        node.parent = right;
        //Changing right and parent references
        right.parent = parent;
        if(parent != null){
            if(parent.left.equals(node)){
                parent.left = right;
            }
            else{
                parent.right = right;
            }
        }
        //New subtree root
        return right;
    }

    private TreapNode bstInsert(TreapNode current, TreapNode insertNode){
        if(current == null){
            return insertNode;
        }

        //Key <= current
        if(((K)insertNode.key).compareTo((K)current.key) < 0){
            current.left = bstInsert(current.left, insertNode);
            current.left.parent = current;
        }
        //Key >= current
        else if(((K)insertNode.key).compareTo((K)current.key) > 0){
            current.right = bstInsert(current.right, insertNode);
            current.right.parent = current;
        }
        return current;
    }

    @Override
    public V remove(K key) {
        TreapNode removeNode = findNode(key);
        if(removeNode == null){
            return null;
        }

        while(removeNode.right != null || removeNode.left != null){
            if(removeNode.left != null && removeNode.right == null){
                rotateRight(removeNode);
            }
            else if(removeNode.right != null && removeNode.left == null){
                rotateLeft(removeNode);
            }
            else if(removeNode.left.priority > removeNode.right.priority){
                rotateRight(removeNode);
            }
            else if(removeNode.right.priority >= removeNode.left.priority){
                rotateLeft(removeNode);
            }
        }

        if(removeNode.parent.left.equals(removeNode)){
            removeNode.parent.left = null;
        }
        else{
            removeNode.parent.right = null;
        }

        return (V)removeNode.value;
    }

    @Override
    public Treap<K, V>[] split(K key) {
        return new Treap[0];
    }

    @Override
    public void join(Treap<K, V> t) {

    }

    //In-order
    @Override
    public Iterator<K> iterator() {
        return null;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        Stack<Integer> level = new Stack<>();
        Stack<TreapNode> s = new Stack<>();

        s.push(root);
        level.push(1);

        while(!s.empty()){
            TreapNode currentNode = s.pop();
            int currentLevel = level.pop();
            result.append(displayLine(currentNode, currentLevel));

            if(currentNode.right != null){
                s.push(currentNode.right);
                level.push(currentLevel + 1);
            }
            if(currentNode.left != null){
                s.push(currentNode.left);
                level.push(currentLevel + 1);
            }
        }

        return result.toString();
    }

    public String displayLine(TreapNode node, int level){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < level - 1; i++){
            result.append("\t");
        }
        String line = "[" + node.priority + "] <" + node.key + ", " + node.getValue() + ">\n";
        result.append(line);
        return result.toString();
    }

    @Override
    public double balanceFactor() throws UnsupportedOperationException {
        return 0;
    }

    @Override
    public void meld(Treap t) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void difference(Treap t) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
