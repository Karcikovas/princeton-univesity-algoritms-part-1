import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int count;

    private class Node {
        Item item;
        Node next;
        Node prev;

        Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {
        first = new Node(null);
        last = new Node(null);
        first.next = last;
        last.prev = first;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }

        Node newNode = new Node(item);
        newNode.next = first.next;
        newNode.prev = first;
        first.next.prev = newNode;
        first.next = newNode;
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item);
        newNode.next = last;
        newNode.prev = last.prev;
        last.prev.next = newNode;
        last.prev = newNode;
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Node newFirst = first.next;
        first.next = newFirst.next;
        first.next.prev = first;
        count--;
        return newFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Node newLast = last.prev;
        last.prev = newLast.prev;
        last.prev.next = last;
        count--;
        return newLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item next = current.item;
            current = current.next;
            return next;
        };
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        Deque<String> deque2 = new Deque<>();
        deque.isEmpty();
        deque.addFirst(2);
        deque.removeLast();
        deque.isEmpty();
        deque.addFirst(5);
        deque.removeLast();

        deque2.addFirst("to chew bubblegum");
        deque2.addLast("and");
        deque2.addFirst("I'm here");
        deque2.addLast("kick ass");
        for (String s: deque2) {
            System.out.print(s + " ");
        }
        deque2.removeFirst();
        deque2.removeLast();
        deque2.addLast("I'm all out of bubblegum");
        deque2.removeFirst();
        System.out.println();
        for (String s: deque2) {
            System.out.print(s + " ");
        }
    }
}
