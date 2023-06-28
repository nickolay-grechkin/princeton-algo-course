import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int head = 0;

    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < head; i++)
            copy[i] = queue[i];
        queue = copy;
    }

    public boolean isEmpty() {
        return head == 0;
    }

    public int size() {
        return head;
    }

    private int getRandomIndex() {
        return StdRandom.uniformInt(head);
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (head == queue.length) {
            resize(2 * queue.length);
        }

        queue[head++] = item;
    }

    public Item dequeue() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        int index = getRandomIndex();
        Item item = queue[index];

        if (size() == 1 || index == size() - 1) {
            queue[index] = null;
        } else {
            queue[index] = queue[size() - 1];
            queue[size() - 1] = null;
        }
        head--;

        if (head > 0 && head == size() / 4) {
            resize(size() / 2);
        };

        return item;
    }

    public Item sample() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        int index = getRandomIndex();
        return queue[index];
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int i = size() - 1;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Item next() {
                int index = getRandomIndex();
                i--;
                return queue[index];
            }
        };
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(14);
        queue.dequeue();
        queue.enqueue(47);
        queue.enqueue(37);
        queue.enqueue(20);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
    }

}
