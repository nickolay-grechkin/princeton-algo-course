import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private static final int virtualSidesQuantity = 2;
    private WeightedQuickUnionUF id;
    private int[][] percolationGrid;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    private int sideLength;

    private int numberOfOpenedSites = 0;
    private int xyToId(int x, int y) {
        return x * sideLength + y;
    }

    private void display2DArray() {
        for (int i = 0; i < percolationGrid.length; i++) {
            for (int j = 0; j < percolationGrid[i].length; j++) {
                System.out.print(percolationGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void validateIndices(int x, int y) {
        if ((x <=0 || y <=0) || (x >= sideLength || y >= sideLength)) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public Percolation(int n) {
        percolationGrid = new int[n][n];
        weightedQuickUnionUF = new WeightedQuickUnionUF((n * n) + virtualSidesQuantity);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    weightedQuickUnionUF.union((n * n + 1), xyToId(i, j));
                }
                percolationGrid[i][j] = -1;
            }
        }

        sideLength = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row, col);
        percolationGrid[row][col] = xyToId(row, col);
        numberOfOpenedSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return percolationGrid[row][col] != -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenedSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.weightedQuickUnionUF.union(percolation.xyToId(1, 1), percolation.xyToId(2, 1));
        System.out.println(percolation.weightedQuickUnionUF.connected(percolation.xyToId(1, 1), percolation.xyToId(2, 1)));
    }
}
