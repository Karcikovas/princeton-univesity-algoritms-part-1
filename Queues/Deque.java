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
        first.prev = last;
        last.next = first;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
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

        Node oldfirst = first;
        first = new Node(item);
        first.next = null;
        first.prev = null;
        count++;

        if(isEmpty()) {
            last = first;
        } else  {
            oldfirst.next = first;
            first.prev = oldfirst;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        Node oldlast = last;
        last = new Node(item);
        last.next = null;
        last.prev = null;
        count++;

        if(isEmpty()) {
            first = last;
        } else {
            last.next = oldlast;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(first == null) {
            throw new NoSuchElementException();
        }

        Node oldFirst = first;
        first = oldFirst.prev;
        count--;

        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(last == null) {
            throw new NoSuchElementException();
        }

        Node oldLast = last;
        last = oldLast.next;
        count--;

        return oldLast.item;
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
            Item item = current.item;
            current = current.prev;
            return item;
        };
    }


    // unit testing (required)
    public static void main(String[] args) {}
}
