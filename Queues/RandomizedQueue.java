import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int lastIndex;
    private Item[] queue;


    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        lastIndex = -1;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return lastIndex + 1;
    }

    @SuppressWarnings("unchecked")
    private void resize(int size) {
        Item[] newQueue = (Item[]) new Object[size];

        for (int i = 0; i < lastIndex + 1; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    // add the item
    public void enqueue(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }

        if (queue.length  == lastIndex + 1) {
            resize(queue.length * 2);
        }

        lastIndex++;
        queue[lastIndex] = item;

    }

    // remove and return a random item
    public Item dequeue() {
        if(size() == 0 ) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniformInt(0, lastIndex + 1);
        Item removedValue = queue[randomIndex];
        queue[randomIndex] = queue[lastIndex];
        queue[lastIndex--] = null;

        if(size() > 0 && size() == queue.length / 4) {
            resize(queue.length/2);
        }

        return removedValue;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(queue.length == 0 ) {
            throw new NoSuchElementException();
        }

        int randomIndex = StdRandom.uniformInt(0, lastIndex + 1);
        return queue[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    };

    private class RandomQueueIterator implements Iterator<Item> {

        private final Item[] copyOfQueue;
        private int copyOfLastIndex;


        RandomQueueIterator() {
            Item[] newQueue = (Item[]) new Object[lastIndex + 1];

            for (int i = 0; i < lastIndex + 1; i++) {
                newQueue[i] = queue[i];
            }
            copyOfQueue = newQueue;
            copyOfLastIndex = lastIndex;

        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return copyOfLastIndex >= 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more item.");
            }

            int randomIndex = StdRandom.uniformInt(0, copyOfLastIndex + 1);
            Item randomValue = copyOfQueue[randomIndex];
            copyOfQueue[randomIndex] = copyOfQueue[copyOfLastIndex];
            copyOfLastIndex--;
            copyOfQueue[lastIndex] = null;
            return randomValue;
        };

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<>();

        // adding 10 elements
        System.out.println("Is queue empty: " + test.isEmpty() + "\n");

        for (int i = 1; i <= 10; i++) {
            test.enqueue(i);
            System.out.println("Added element: " + i);
            System.out.println("Current number of elements in queue: " + test.size() + "\n");

        }


        System.out.print("\nIterator test:\n[");
        for (Integer elem: test)
            System.out.print(elem + " ");
        System.out.println("]\n");

        // removing 10 elements
        for (int i = 0; i < 10; i++) {
            System.out.println("Removed element: " + test.dequeue());
            System.out.println("Current number of elements in queue: " + test.size() + "\n");
        }

    }

}
