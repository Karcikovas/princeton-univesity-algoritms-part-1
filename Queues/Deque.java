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
        newNode.next = last.next;
        newNode.prev = last;
        last.next.prev = newNode;
        last.next = newNode;
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(!isEmpty()) {
            throw new NoSuchElementException();
        }
        Node newFirst = first.next;
        first.next = newFirst.next;
        first.next.prev = newFirst;
        count--;

        return newFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(!isEmpty()) {
            throw new NoSuchElementException();
        }
        Node newLast = last.prev;
        last.prev = newLast.prev;
        last.prev.next = newLast;
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
        Deque<String> deque = new Deque<>();
        deque.addFirst("to chew bubblegum");
        deque.addLast("and");
        deque.addFirst("I'm here");
        deque.addLast("kick ass");
        for (String s: deque) {
            System.out.print(s + " ");
        }
        deque.removeFirst();
        deque.removeLast();
        deque.addLast("I'm all out of bubblegum");
        deque.removeFirst();
        System.out.println();
        for (String s: deque) {
            System.out.print(s + " ");
        }
    }
}
