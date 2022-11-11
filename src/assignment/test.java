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

        TreapMap<Integer, Integer> t = new TreapMap<>(1, 1);


        System.out.println(t.toString());
//        t.insert(10, 10);
//        t.insert(7, 7);
        t.insert(4, 4);
        //t.insert(3, 21);
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
        Treap<Integer, Integer>[] splitTreap = t.split(6);
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
