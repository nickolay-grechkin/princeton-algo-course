import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF id;
    private int[][] percolationGrid;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    private int sideLength;

    private int xyToId(int x, int y) {
        return x * sideLength + y;
    }

    public Percolation(int n) {
        percolationGrid = new int[n][n];
        int index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                percolationGrid[i][j] = index;
                index++;
            }
        }

        sideLength = n;
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        System.out.println();
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        System.out.println(percolation.weightedQuickUnionUF);
    }

}
