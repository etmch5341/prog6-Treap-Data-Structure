package assignment;

import java.sql.SQLOutput;
import java.util.Iterator;

public class test2 {
    public static void main(String[] args) {
        TreapMap<Integer, Integer> t = new TreapMap<>(1, 1);
        Iterator<Integer> i = t.iterator();

        System.out.println(i.next());
//        System.out.println(i.next());
        //System.out.println(t.toString());
        t.insert(4, 4, 4743); //test case whether it stays the same
        //System.out.println(t.toString());
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.next());

        System.out.println();

        TreapMap<Integer, Integer> t2 = new TreapMap<>(1, 1);
        Iterator<Integer> i2 = t2.iterator();
        while(i2.hasNext()){
            System.out.println(i2.next());
        }
    }
}
