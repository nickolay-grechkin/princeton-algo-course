import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private static final int virtualSidesQuantity = 2;

    private final boolean[][] percolationGrid;
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    private final int firstVirtualSideIndex;
    private final int secondVirtualSideIndex;
    private final int percolationGridLength;
    private final int sideLength;

    private int numberOfOpenedSites = 0;
    private int xyToId(int x, int y) {
        validateIndices(x, y);
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
        if ((x < 0 || y < 0) || (x >= sideLength || y >= sideLength)) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public Percolation(int n) {
        weightedQuickUnionUF = new WeightedQuickUnionUF((n * n) + virtualSidesQuantity);
        sideLength = n;
        percolationGridLength = sideLength * sideLength;

        firstVirtualSideIndex = (percolationGridLength + (virtualSidesQuantity - 1)) - 1;
        secondVirtualSideIndex = (percolationGridLength + virtualSidesQuantity) - 1;

        percolationGrid = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    weightedQuickUnionUF.union(firstVirtualSideIndex, xyToId(i, j));
                }
                if (i == (sideLength - 1)) {
                    weightedQuickUnionUF.union(secondVirtualSideIndex, xyToId(i, j));
                }

                percolationGrid[i][j] = false;
            }
        }
    }

    public boolean connected(int p, int q) {
        return weightedQuickUnionUF.find(p) == weightedQuickUnionUF.find(q);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row, col);
        if (percolationGrid[row][col]) {
            return;
        }
        percolationGrid[row][col] = true;
        numberOfOpenedSites++;

//        boolean isBottomOpened = isOpen(row - 1, col);
//        boolean isTopOpened = isOpen(row + 1, col);
//        boolean isLeftOpened = isOpen(row, col - 1);
//        boolean isRightOpened = isOpen(row, col + 1);
//
//        if (!isBottomOpened) return;
//        if (!isTopOpened) return;
//        if (!isRightOpened) return;
//        if (!isLeftOpened) return;
//
//        return { };
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row - 1, col - 1);
        return percolationGrid[row - 1][col - 1];
    }

    // is the site (row, col) connected to the top row?
    public boolean isFull(int row, int col) {
        validateIndices(row - 1, col - 1);
        if (!isOpen(row, col)) return false;
        return connected(xyToId(row - 1, col - 1), firstVirtualSideIndex);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenedSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return connected(firstVirtualSideIndex, secondVirtualSideIndex);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(0, 0);
        percolation.open(1, 1);
        percolation.open(2, 2);
        percolation.open(3, 3);
        percolation.open(4, 4);
        percolation.weightedQuickUnionUF.union(percolation.xyToId(0, 0), percolation.xyToId(1, 1));
        percolation.weightedQuickUnionUF.union(percolation.xyToId(1, 1), percolation.xyToId(2, 2));
        percolation.weightedQuickUnionUF.union(percolation.xyToId(2, 2), percolation.xyToId(3, 3));
        percolation.weightedQuickUnionUF.union(percolation.xyToId(3, 3), percolation.xyToId(4, 4));
        System.out.println("Percolates: " + percolation.percolates());
        System.out.println("Number of open sites: " + percolation.numberOfOpenedSites);
    }
}
