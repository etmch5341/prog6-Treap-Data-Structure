package assignment;

import java.util.Iterator;
import java.util.Stack;

public class TreapIterator implements Iterator {
    TreapMap treap;

    private Stack<TreapNode> s;
    public TreapIterator(TreapNode root){
        treap = new TreapMap((Comparable) root.key, root.value, root.priority);
        instantiateTreap(root);
        s = new Stack<>();
        pushLeft(treap.getRoot());
    }

    @Override
    public boolean hasNext() {
        return !s.empty();
    }

    @Override
    public Object next() {
        TreapNode current = s.pop();
        pushLeft(current.right);
        return current.key;
    }

    public void pushLeft(TreapNode node){
        while(node != null){
            s.push(node);
            node = node.left;
        }
    }

    public void instantiateTreap(TreapNode root){
        Stack<TreapNode> tempS = new Stack<>();

        tempS.push(root);

        while(!tempS.empty()){
            TreapNode currentNode = tempS.pop();
            treap.insert((Comparable) currentNode.key, currentNode.value, currentNode.priority);

            if(currentNode.right != null){
                tempS.push(currentNode.right);
            }
            if(currentNode.left != null){
                tempS.push(currentNode.left);
            }
        }
    }
}
