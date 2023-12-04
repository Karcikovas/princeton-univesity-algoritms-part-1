import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private double[] fractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }

        fractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);


            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;


                percolation.open(row, col);
            }

            fractions[i] = percolation.numberOfOpenSites() * 1.0 / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(fractions.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(fractions.length);
    }

    // test client (see below)
    public static void main(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException();
        }

        int gridSize = 0;
        int trials = 0;

        try {
            gridSize = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        } finally {}

        PercolationStats stats = new PercolationStats(gridSize, trials);

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() +", " + stats.confidenceHi() + "]");
    }
}
