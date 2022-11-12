package assignment;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
//        List<Integer> s = new ArrayList<>();
//
//        System.out.println(t.toString());
//        Integer six = 6;
//        Integer five = 5;
//
//        System.out.println(five.compareTo(six));
//
//        System.out.println(t.lookup(2));

        TreapMap<Integer, Integer> t = new TreapMap<>(6, 1, 9403);

        //Test: Figure 2 Treap
//        root = new TreapNode(6, 1, null, 9403);
//        root.left = new TreapNode(3, 2, root, 4407);
//        root.right = new TreapNode(8, 3, root, 7336);
//        root.left.left = new TreapNode(1, 4, root.left, 2486);
//        root.left.right = new TreapNode(5, 5, root.left, 1059);
//        root.right.right = new TreapNode(9, 6, root.right, 1963);

        System.out.println(t.toString());

        t.insert(6, 1, 9403);
        t.insert(3, 2, 4407);
        t.insert(8, 3, 7336);
        t.insert(1, 4, 2486);
        t.insert(5, 5, 1059);
        t.insert(9, 6, 1963);
        t.insert(4, 4, 4743);
        System.out.println("insert 4");
        System.out.println(t.toString());
        //System.out.println(t.lookup(7));
        System.out.println("--------------------");
        t.remove(4);
        System.out.println("remove 4");
        System.out.println(t.toString());
        System.out.println("--------------------");
        t.remove(10);
        System.out.println("remove 10");
        System.out.println(t.toString());
        System.out.println("--------------------");

        //TESTING SPLIT
        Treap<Integer, Integer>[] splitTreap = t.split(10);
        System.out.println("left subtreap");
        System.out.println(splitTreap[0].toString());
        System.out.println("right subtreap");
        System.out.println(splitTreap[1].toString());

        //TESTING JOIN
        TreapMap<Integer, Integer> left = new TreapMap<>(6, 6, 9403);
        left.insert(3, 3, 4407);
        left.insert(1, 1, 2486);
        left.insert(5, 5, 1059);
        TreapMap<Integer, Integer> right = new TreapMap<>(8, 8, 7336);
        right.insert(9, 9, 1936);

        System.out.println("LEFT");
        System.out.println(left.toString());
        System.out.println("-----------------------");
        System.out.println("RIGHT");
        System.out.println(right.toString());

        left.join(right);
        System.out.println("JOINING");
        System.out.println(left.toString());
    }
}
