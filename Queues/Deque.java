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
        return first == null;
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
        newNode.next = first;

        if(first == null && last == null) {
            last = newNode;
        } else if (first != null) {
            first.prev = newNode;
        }

        first = newNode;
        count++;
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
        Node node = first;

        if (first.next == null) {
            last = null;
        }
        else {
            first.next.prev = null;
        }

        first = first.next;
        count--;
        return node.item;
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
