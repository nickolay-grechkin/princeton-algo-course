import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int k = 5;
        int index = 0;
        while (index != k) {
            String item = StdIn.readString();
            queue.enqueue(item);
            index++;
        }

        Iterator<String> iterator = queue.iterator();

        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }
    }
}

