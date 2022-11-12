package assignment;

import java.util.Iterator;
import java.util.Stack;

public class TreapMap<K extends Comparable<K>, V> implements Treap<K, V> {
    private TreapNode root;

    /**
     * Constructor: creates a new root node with key value
     * @param key
     * @param value
     */
    public TreapMap(K key, V value){
        root = new TreapNode(key, value, null);
    }

    /**
     * Constructor: creates new null root
     */
    public TreapMap(){
        root = null;
    }

    /**
     * Contructor: creates new root node with key, value, and priority
     * @param key
     * @param value
     * @param priority
     */
    public TreapMap(K key, V value, int priority){
        root = new TreapNode(key, value, null, priority);
    }

    /**
     * Constructor: creates new root with root parameter
     * @param root
     */
    public TreapMap(TreapNode root){
        this.root = root;
    }

    /*
        Looks for the key in the Treap and returns the value associated with it.
        If the key is null or the root is null it returns null.
     */
    @Override
    public V lookup(K key) {
        //Checks for null key case
        if(key == null){
            return null;
        }

        //Will search through the Treap by the BST property
        //Will stop searching once the current is null or if it finds the key
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

        //If root node is null
        if(current == null){
            return null;
        }
        return (V)current.getValue();
    }

    /*
        Inserts a node with the key-value pair to the Treap.
        First, it will insert the key-value pair by the BST property. Then it will fix the treap
        so that it satisfies the Heap property.
     */
    @Override
    public void insert(K key, V value) {
        //Checks for null key and null value case
        if(key == null || value == null){
            return;
        }

        TreapNode<K, V> insertNode = new TreapNode(key, value, null);
        //Checks for null root case
        if(root == null){
            root = insertNode;
            return;
        }

        //Checks if key exists in the Treap already and simply updates the value only
        if(lookup(key) != null){
            findNode(key).value = value;
            return;
        }

        //First inserts node by the BST property, then fixes the treap by the Heap property through rotations
        bstInsert(root, insertNode);
        while(insertNode.parent != null && insertNode.parent.priority < insertNode.priority){
            //Checks for if the parent left or right is null
            if(insertNode.parent.left == null && insertNode.parent.right != null){
                rotateLeft(insertNode.parent);
            }
            else if(insertNode.parent.left != null && insertNode.parent.right == null){
                rotateRight(insertNode.parent);
            }
            //Finds if the node to insert is the left or right child
            else if(insertNode.parent.left.equals(insertNode)){
                rotateRight(insertNode.parent);
            }
            else if(insertNode.parent.right.equals(insertNode)){
                rotateLeft(insertNode.parent);
            }
        }
    }

    /**
     * TESTING INSERT METHOD TO SET PRIORITIES
     * Insert method for defined priority values. Used in iterator to create a new copy.
     *
     * @param key           Key to insert in treap
     * @param value         Value associated with key to insert
     * @param priority      Priority associated with key-value pair
     */
    public void insert(K key, V value, int priority) {
        TreapNode<K, V> insertNode = new TreapNode(key, value, null, priority);
        if(root == null){
            root = insertNode;
            return;
        }

        if(lookup(key) != null){
            findNode(key).value = value;
            return;
        }

        bstInsert(root, insertNode);

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


    /**
     * Insert method for TreapNodes and follows the same logic as the other insert methods
     * @param node
     */
    private void insertNode(TreapNode node){
        if(root == null){
            root = node;
            return;
        }
//        System.out.println("before");
//        System.out.println(toString());
        bstInsert(root, node);
//        System.out.println("BST Insert");
//        System.out.println(toString());
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
     * @param key       Key to find
     * @return          TreapNode associated with the key
     */
    private TreapNode findNode(K key){
        if(key == null){
            return null;
        }
        TreapNode current = root;
        //Does a BST search for the key
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

    /**
     * Rotates the TreapNodes around the specified TreapNode.
     * Does variable reassignments for the parent, node, and left child
     *
     * @param node
     * @return
     */
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

    /**
     * Inserts a TreapNode by the BST property recursively. If the insertNode's key is less
     * than the current node's key, then it calls the method and passes in the left node.
     * If the insertNode's key is greater than the current node's key, then it calls the method
     * and passes in the right node. Once it reaches the leaf node it goes back up the recursive call
     * stack and assigns the left and right nodes.
     *
     * @param current           Current TreapNode to check the key for
     * @param insertNode        TreapNode to be inserted
     * @return                  The current node
     */
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

    /*
        Removes the key from the Treap and returns the value associated with the key.
        After searching for the key, if it cannot find it, then it will return null.

        To remove the key, it will rotate the key until it is a leaf node. It checks if
        either the left or right is null. If the left is null and the right is not null, it will
        rotate right. If the right is null and the left is no null, it will rotate left. Then it checks
        if the left priority is greater than the right priority, it will rotate right. Then it checks
        if the right priority is greater than or equal to the left priority, it will rotate left.
        Finally, it updates the access to either the left, right, or root variable.
     */
    @Override
    public V remove(K key) {
        //Calls the findNode method to search for the key. If the key doesn't exist then it will set removeNode to null
        TreapNode removeNode = findNode(key);
        if(removeNode == null){
            return null;
        }

        //Will
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

        if(removeNode.equals(root)){
            root = null;
        }
        else if(removeNode.parent.left != null && removeNode.parent.left.equals(removeNode)){
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
            //Either make null or make new TreapMap with empty root
//            splitTreap[0] = new TreapMap<>(null);
//            splitTreap[1] = new TreapMap<>(null);
//            splitTreap[0] = null;
//            splitTreap[1] = null;
//            return splitTreap;
            return null;
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
                splitTreap[0] = new TreapMap(leftSubtreap);
            }
            else{
                splitTreap[0] = null;
            }
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
                subRoot.left = null;
                splitTreap[0] = new TreapMap(leftSubtreap);
            }
            else{
                splitTreap[0] = null;
            }
            if(rightSubtreap != null){
                rightSubtreap.parent = null;
                subRoot.right = null;
                splitTreap[1] = new TreapMap(rightSubtreap);
            }
            else{
                splitTreap[1] = null;
            }
        }

        return splitTreap;
    }

    //Can guarentee that the keys in one treap are strictly greater than another
    @Override
    public void join(Treap<K, V> t) {
        if(!(t instanceof TreapMap)){
            return;
        }
        if(root == null){
            return;
        }
        if(t == null || ((TreapMap)t).root == null){
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

        if(root == null || root.key == null || root.value == null){
            return "";
        }

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
