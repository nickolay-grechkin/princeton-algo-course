import java.util.Iterator;
import java.util.NoSuchElementException;

import static javax.swing.text.html.HTML.Attribute.N;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size = 0;

    private class Node {
        Item item;
        Node next;

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public Deque() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldFirst = head;
        head = new Node(item, oldFirst);

        if (tail == null) {
            tail = head;
        }

        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node newNode = new Node(item, null);

        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;

        if (head == null) {
            head = newNode;
        }

        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = head.item;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item removedItem = tail.item;

        if (size == 1) {
            tail = null;
            head = null;
        } else {
            Node pointer = head;
            for (int i = 0; i < size - 2; i++) {
                pointer = pointer.next;
            }
            pointer.next = null;
            tail = pointer;
        }

        size--;

        return removedItem;
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = head;
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (head == null) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("First");
        deque.addFirst("Second");
        deque.addFirst("Third");
        deque.addFirst("Fourth");
        deque.removeFirst();
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        Iterator<String> dequeIterator = deque.iterator();

        while(dequeIterator.hasNext()) {
            String s = dequeIterator.next();
            System.out.println(s);
        }
    }
}

