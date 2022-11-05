package assignment;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Integer> s = new ArrayList<>();

//        TreapTest<Integer, Integer> t = new TreapTest<>(1, 1);
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
        System.out.println(t.toString());
        //System.out.println(t.lookup(7));
        t.remove(4);
        System.out.println(t.toString());
        t.remove(10);
        System.out.println(t.toString());
        Treap<Integer, Integer>[] splitTreap = t.split(10);
        System.out.println(splitTreap[0].toString());
        System.out.println(splitTreap[1].toString());

    }
}
