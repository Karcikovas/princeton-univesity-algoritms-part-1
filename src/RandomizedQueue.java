import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int lastIndex;
    private Item[] queue;
    private Random random = new Random();


    // construct an empty randomized queue
    public RandomizedQueue() {
        Item[] newQueue =  (Item[]) new Object[1];
        queue = newQueue;
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

    private void resize(int size) {
        Item[] newArray = Arrays.copyOfRange(queue, 0 , size);
        queue = newArray;
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
        if(queue.length == 0 ) {
            throw new NoSuchElementException();
        }

        if(lastIndex <= queue.length / 2) {
            resize(queue.length/2);
        }

        int randomIndex = random.nextInt(lastIndex + 1);
        Item randomValue = queue[randomIndex];
        queue[randomIndex] = queue[lastIndex];
        queue[lastIndex] = null;

        return randomValue;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(queue.length == 0 ) {
            throw new NoSuchElementException();
        }

        int randomIndex = random.nextInt(lastIndex + 1);
        return queue[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    };

    private class RandomQueueIterator implements Iterator<Item> {

        private Item[] copyOfQueue;
        private int copyOfLastIndex;


        RandomQueueIterator() {
            copyOfQueue= Arrays.copyOfRange(queue, 0, lastIndex);
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

            int randomIndex = random.nextInt(copyOfLastIndex + 1);
            Item randomValue = queue[randomIndex];
            queue[randomIndex] = queue[copyOfLastIndex];
            copyOfLastIndex--;
            queue[lastIndex] = null;
//            System.out.println("copyOfLastIndex: " + copyOfLastIndex + "\n");
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
