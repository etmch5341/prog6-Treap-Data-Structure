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
//        root = new TreapNode(6, 1, null, 9403);
//        root.left = new TreapNode(3, 2, root, 4407);
//        root.right = new TreapNode(8, 3, root, 7336);
//        root.left.left = new TreapNode(1, 4, root.left, 2486);
//        root.left.right = new TreapNode(5, 5, root.left, 1059);
//        root.right.right = new TreapNode(9, 6, root.right, 1963);
    }

    public TreapMap(K key, V value, int priority){
        root = new TreapNode(key, value, null, priority);
    }

    public TreapMap(TreapNode root){
        this.root = root;
    }

    @Override
    public V lookup(K key) {
        TreapNode current = root;
        while(current != null && key.compareTo((K)current.key) != 0){
            //Key < current
            if(key.compareTo((K)current.key) < 0){
                current = current.left;
            }
            //Key > current
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
        //TODO: Make sure that you don't use priority constructor
        //TreapNode<K, V> insertNode = new TreapNode(key, value, null, 4743);
        TreapNode<K, V> insertNode = new TreapNode(key, value, null);
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
        while(insertNode.parent != null && insertNode.parent.priority < insertNode.priority){
            if(insertNode.parent.left == null && insertNode.parent.right != null){
                rotateLeft(insertNode.parent);
            }
            else if(insertNode.parent.left != null && insertNode.parent.right == null){
                rotateRight(insertNode.parent);
            }
            else if(insertNode.parent.left.equals(insertNode)){
                rotateRight(insertNode.parent);
            }
            else if(insertNode.parent.right.equals(insertNode)){
                rotateLeft(insertNode.parent);
            }
        }
    }

    //TESTING INSERT METHOD TO SET PRIORITIES
    public void insert(K key, V value, int priority) {
        //TODO: Make sure that you don't use priority constructor
        TreapNode<K, V> insertNode = new TreapNode(key, value, null, priority);
        //TreapNode<K, V> insertNode = new TreapNode(key, value, null);
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
        while(insertNode.parent != null && insertNode.parent.priority < insertNode.priority){
            if(insertNode.parent.left == null && insertNode.parent.right != null){
                rotateLeft(insertNode.parent);
            }
            else if(insertNode.parent.left != null && insertNode.parent.right == null){
                rotateRight(insertNode.parent);
            }
            else if(insertNode.parent.left.equals(insertNode)){
                rotateRight(insertNode.parent);
            }
            else if(insertNode.parent.right.equals(insertNode)){
                rotateLeft(insertNode.parent);
            }
        }
    }

    //TODO: Probably can remove this method and just
    // use the overloaded method that have a priority as parameter
    private void insertNode(TreapNode node){
        System.out.println("before");
        System.out.println(toString());
        bstInsert(root, node);
        System.out.println("BST Insert");
        System.out.println(toString());
        //System.out.println(insertNode.parent.key);
        while(node.parent != null && node.parent.priority < node.priority){
            if(node.parent.left == null && node.parent.right != null){
                rotateLeft(node.parent);
            }
            else if(node.parent.left != null && node.parent.right == null){
                rotateRight(node.parent);
            }
            else if(node.parent.left.equals(node)){
                rotateRight(node.parent);
            }
            else if(node.parent.right.equals(node)){
                rotateLeft(node.parent);
            }
        }

        System.out.println(toString());
    }

    /**
     * Finds the node with specified key and returns the node if it is found.
     * If it does not exist in the treap, it will return null.
     * @param key
     * @return
     */
    private TreapNode findNode(K key){
        TreapNode current = root;
        while(current != null && !current.key.equals(key)){
            //Key < current key
            if(key.compareTo((K)current.key) < 0){
                current = current.left;
            }
            //Key > current key
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
        //Changing left and parent reference
        left.parent = parent;
        if(parent != null){
            if(parent.left != null && parent.left.equals(node)){
                parent.left = left;
            }
            else{
                parent.right = left;
            }
        }
        //Update root if node is root
        if(node.equals(root)){
            root = left;
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
            if(parent.left != null && parent.left.equals(node)){
                parent.left = right;
            }
            else{
                parent.right = right;
            }
        }
        //Update root if node is root
        if(node.equals(root)){
            root = right;
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

        if(removeNode.parent.left != null && removeNode.parent.left.equals(removeNode)){
            removeNode.parent.left = null;
        }
        else{
            removeNode.parent.right = null;
        }

        return (V)removeNode.value;
    }

    @Override
    public Treap<K, V>[] split(K key) {
        TreapNode<K, V> subRoot = new TreapNode<>(key, null, null, Treap.MAX_PRIORITY + 1);
        Treap<K, V>[] splitTreap = new TreapMap[2];
        int tempPriority = -1;
        V tempValue = null;

        //Null root case and key null case
        if(root == null || key == null){
            splitTreap[0] = null;
            splitTreap[1] = null;
            return splitTreap;
        }

        //Check for case where key exists in the Treap
        if(lookup(key) != null){
            //subRoot = (TreapNode<K, V>) (findNode(key)).clone();
            TreapNode tempNode = findNode(key);
            tempPriority = tempNode.priority;
            tempValue = (V)tempNode.value;
            remove(key);
        }

        //Make subroot root
        insertNode(subRoot);

        //Assign left and right subtreap
        if(tempPriority != -1){
            TreapNode<K, V> leftSubtreap = subRoot.left;
            if(leftSubtreap != null){
                leftSubtreap.parent = null;
            }
            splitTreap[0] = new TreapMap(leftSubtreap);
            subRoot.priority = tempPriority;
            subRoot.value = tempValue;
            subRoot.left = null;
            splitTreap[1] = new TreapMap(subRoot);
        }
        else{
            TreapNode<K, V> leftSubtreap = subRoot.left;
            TreapNode<K, V> rightSubtreap = subRoot.right;
            if(leftSubtreap != null){
                leftSubtreap.parent = null;
            }
            if(rightSubtreap != null){
                rightSubtreap.parent = null;
            }
            splitTreap[0] = new TreapMap(leftSubtreap);
            splitTreap[1] = new TreapMap(rightSubtreap);
        }

        return splitTreap;
    }

    //Can guarentee that the keys in one treap are strictly greater than another
    @Override
    public void join(Treap<K, V> t) {
        if(!(t instanceof TreapMap)){
            return;
        }
        TreapMap<K, V> joinTreap = new TreapMap<>((K)root.key, (V)root.value);
        K cKey = (K)root.key;
        K tKey = ((K)((TreapMap)t).getKey());
        //tKey < cKey
        if(tKey.compareTo(cKey) < 0){
            joinTreap.root.left = ((TreapMap)t).root;
            joinTreap.root.right = root;
        }
        //tKey > cKey
        else if(tKey.compareTo(cKey) > 0){
            joinTreap.root.left = root;
            joinTreap.root.right = ((TreapMap)t).root;
        }
        joinTreap.remove((K)joinTreap.root.key);
        root = joinTreap.root;
    }

    //In-order
    @Override
    public Iterator<K> iterator() {
        return new TreapIterator(root);
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

    public K getKey(){
        return (K)root.key;
    }

    public TreapNode getRoot(){
        return root;
    }
}
