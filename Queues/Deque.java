public class Deque<Item> {
    private Node first = null;
    private Node last = null;
    private int count = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = last;
        count = 0;
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

        Node oldfirst = first;
        first = new Node();
        first.item = item;
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
        last = new Node();
        last.item = item;
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
    //    public Item removeFirst() {
    //
    //    }

    // remove and return the item from the back
    //    public Item removeLast() {
    //
    //    }

    // return an iterator over items in order from front to back
    //    public Iterator<Item> iterator() {
    //
    //    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        d.addLast("Hey");
        d.addLast("Ho");
        System.out.println(d.size());
    }
}
