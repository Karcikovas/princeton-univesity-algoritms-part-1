import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int topRoot = 0;
    private int bottomRoot = 0;
    private int openSites = 0;

    private int gridSize;
    private WeightedQuickUnionUF perc;
    private WeightedQuickUnionUF fullSites;
    private boolean [] grid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if( n < 1) {
            throw new IllegalArgumentException("Grid must have at least one row and one column");
        }

        gridSize = n;
        // + 2 because of virtual nodes
        perc = new WeightedQuickUnionUF(n * n + 2);
        // no bottom index is needed for fullsites
        fullSites = new WeightedQuickUnionUF(n * n + 1);
        topRoot = n * n;
        bottomRoot = n * n + 1;
        grid = new boolean[n * n + 2];
        grid[topRoot] = true;
        grid[bottomRoot] = true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);

        if (isOpen(row, col)) return;

        int currentIndex = index(row, col);

        // Top
        if (row == 1) {
            perc.union(currentIndex, topRoot);
            fullSites.union(currentIndex, topRoot);
        } else {
            int topRow = row - 1;
            unionIfOpen(topRow, col, currentIndex);
        }

        // Right
        if (col < gridSize) {
            int rightCol = col + 1;
            unionIfOpen(row, rightCol, currentIndex);
        }

        // Bottom
        if (row == gridSize) {
            perc.union(currentIndex, bottomRoot);
        } else {
            int bottomRow = row + 1;
            unionIfOpen(bottomRow, col, currentIndex);
        }

        // Left
        if (col > 1) {
            int leftCol = col - 1;
            unionIfOpen(row, leftCol, currentIndex);
        }

        grid[(row - 1 ) * gridSize + (col - 1)] = true;
        openSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        check(row, col);


        return grid[(row - 1 ) * gridSize + (col - 1)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        check(row, col);

        int index = index(row, col);
        return fullSites.find(index) == fullSites.find(topRoot);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return perc.find(topRoot) == perc.find(bottomRoot);
    }

    private void check(int row, int column) {
        if(row < 1 || row > gridSize || column < 1 || column > gridSize) {
            throw new IllegalArgumentException("Column and row can't be bigger than grid size itself");
        }
    }

    private int index(int row, int col) {
        return gridSize * (row - 1) + (col - 1);
    }

    private void unionIfOpen(int row, int col, int indexToUnion) {
        if (isOpen(row, col)) {
            int index = index(row, col);
            perc.union(index, indexToUnion);
            fullSites.union(index, indexToUnion);
        }
    }

    public static void main(String[] args) {}
}
