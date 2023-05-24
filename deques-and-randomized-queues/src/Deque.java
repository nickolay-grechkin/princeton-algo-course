import java.util.Iterator;

import static javax.swing.text.html.HTML.Attribute.N;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private Node oldTail;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldFirst = head;
        head = new Node();
        head.item = item;
        head.next = oldFirst;
        tail = oldFirst;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        if (tail == null) {
            tail = newNode;
        } else {
            tail.next = newNode;
        }

        oldTail = tail;

        if (head == null) {
            head = newNode;
        }

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = head.item;
        head = head.next;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item item = tail.item;
        oldTail.next = null;
        tail = oldTail;
        return item;
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = head;
        public boolean hasNext() { return current != null; }
        public void remove() { /* not supported */ }
        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addLast("F");
        deque.addLast("S");
        deque.addFirst("T");
        deque.addFirst("G");
        deque.removeLast();
        deque.removeLast();
        Iterator<String> dequeIterator = deque.iterator();

        while(dequeIterator.hasNext()) {
            String s = dequeIterator.next();
            System.out.println(s);
        }
    }
}

