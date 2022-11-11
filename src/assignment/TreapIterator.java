package assignment;

import java.util.Iterator;
import java.util.Stack;

public class TreapIterator implements Iterator {
    TreapNode root;

    private Stack<TreapNode> s;
    public TreapIterator(TreapNode root){
        this.root = root;
        s = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    public void pushLeft(TreapNode node){

    }
}
