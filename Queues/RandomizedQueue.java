import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
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


    // construct an empty randomized queue
    public RandomizedQueue() {
        last = new Node(null);
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        Node last = new Node(item);
        last.next = oldLast;
        last.prev = oldLast.prev;
        count++;

    }

    // remove and return a random item
    public Item dequeue() {

    }

    // return a random item (but do not remove it)
    public Item sample() {

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

    }

    // unit testing (required)
    public static void main(String[] args) {
        
    }

}
