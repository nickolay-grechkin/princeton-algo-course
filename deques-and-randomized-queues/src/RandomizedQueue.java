import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    Item[] queue;
    int head = 0;
    int tail = 0;
    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        System.arraycopy(queue, 0, copy, 0, queue.length);
        queue = copy;
    }
    // is the randomized queue empty?
    public boolean isEmpty() {
        return queue.length == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return queue.length;
    }

    // add the item
    public void enqueue(Item item) {
        if (tail == queue.length) {
            resize(2 * queue.length);
        }

        queue[tail++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int i = queue.length;
            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public Item next() {
                return queue[--i];
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("1");
        queue.enqueue("2");

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }
    }

}
