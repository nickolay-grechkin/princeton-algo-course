import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int VIRTUAL_SIDES_QUANTITY = 2;

    private final boolean[][] percolationGrid;
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    private final int firstVirtualSideIndex;
    private final int secondVirtualSideIndex;
    private final int sideLength;

    private int numberOfOpenedSites = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        weightedQuickUnionUF = new WeightedQuickUnionUF((n * n) + VIRTUAL_SIDES_QUANTITY);
        sideLength = n;
        int percolationGridLength = sideLength * sideLength;

        firstVirtualSideIndex = (percolationGridLength + (VIRTUAL_SIDES_QUANTITY - 1)) - 1;
        secondVirtualSideIndex = (percolationGridLength + VIRTUAL_SIDES_QUANTITY) - 1;

        percolationGrid = new boolean[n][n];
    }

    private int xyToId(int x, int y) {
        validateIndices(x, y);
        return x * sideLength + y;
    }

    private void validateIndices(int x, int y) {
        if ((x < 0 || y < 0) || (x >= sideLength || y >= sideLength)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isConnected(int p, int q) {
        return weightedQuickUnionUF.find(p) == weightedQuickUnionUF.find(q);
    }

    private void bfs (int r, int c) {
            int row = r;
            int col = c;

            int[][] directions = {{ 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }};

            for (int[] direction : directions) {
                r = row + direction[0];
                c = col + direction[1];
                if ((r >= 0 && r < sideLength) && (c >= 0 && c < sideLength) && percolationGrid[r][c]) {
                    weightedQuickUnionUF.union(xyToId(row, col), xyToId(r, c));
                }
            }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row - 1, col - 1);
        if (percolationGrid[row - 1][col - 1]) {
            return;
        }
        if (!isConnected(firstVirtualSideIndex, xyToId(0, 0))) {
            for (int i = 0; i < sideLength; i++) {
                weightedQuickUnionUF.union(firstVirtualSideIndex, xyToId(0, i));
                weightedQuickUnionUF.union(secondVirtualSideIndex, xyToId(sideLength - 1, i));
            }
        }
        percolationGrid[row - 1][col - 1] = true;
        numberOfOpenedSites++;
        bfs(row - 1, col - 1);
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
        return isConnected(xyToId(row - 1, col - 1), firstVirtualSideIndex);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenedSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return isConnected(firstVirtualSideIndex, secondVirtualSideIndex);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(0, 0);
        percolation.open(1, 1);
        percolation.open(2, 2);
        percolation.open(3, 3);
        percolation.open(4, 4);
        percolation.open(5, 5);
        percolation.open(6, 6);
        System.out.println(percolation.isFull(6, 6));
    }
}
