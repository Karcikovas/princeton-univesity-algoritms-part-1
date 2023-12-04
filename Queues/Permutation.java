import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        final int k = Integer.parseInt(args[0]);
        int count = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            count++;
            if (queue.size() < k) {
                queue.enqueue(token);
            }
            else {
                if (StdRandom.uniform() < (double) k / count) {
                    queue.dequeue();
                    queue.enqueue(token);
                }
            }
        }

        for (String s : queue) {
            System.out.println(s);
        }
    }
}
