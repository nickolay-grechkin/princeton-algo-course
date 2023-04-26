import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Percolation {
    private static final int virtualSidesQuantity = 2;

    private final boolean[][] percolationGrid;
    private final boolean[] openedSites;
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    private final int firstVirtualSideIndex;
    private final int secondVirtualSideIndex;
    private final int percolationGridLength;
    private final int sideLength;
    int rows;
    int cols;
    char[][] grid;

    private int numberOfOpenedSites = 0;
    private int xyToId(int x, int y) {
        validateIndices(x, y);
        return x * sideLength + y;
    }

    private void display2DArray() {
        for (boolean[] booleans : percolationGrid) {
            for (boolean aBoolean : booleans) {
                System.out.print(aBoolean + " ");
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
        openedSites = new boolean[percolationGridLength];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    weightedQuickUnionUF.union(firstVirtualSideIndex, xyToId(i, j));
                }
                if (i == (sideLength - 1)) {
                    weightedQuickUnionUF.union(secondVirtualSideIndex, xyToId(i, j));
                }
                openedSites[index] = false;
                percolationGrid[i][j] = false;
                index++;
            }
        }
    }

    public boolean connected(int p, int q) {
        return weightedQuickUnionUF.find(p) == weightedQuickUnionUF.find(q);
    }

    public void bfs (int r, int c) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(r + "," + c);
        visited.add(r + "," + c);

        while (!q.isEmpty()) {
            String[] qElement = q.remove().split(",");
            int row = Integer.parseInt(qElement[0]);
            int col = Integer.parseInt(qElement[1]);

            int[][] directions = {{ 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { -1, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 }};

            for (int[] direction : directions) {
                r = row + direction[0];
                c = col + direction[1];
                System.out.println(r + " " + c);
                if ((r >= 0 && r < sideLength) && (c >= 0 && c < sideLength) && percolationGrid[r][c] && !visited.contains(r + "," + c)) {
                    q.add(r + "," + c);
                    visited.add(r + "," + c);
                    weightedQuickUnionUF.union(xyToId(row, col), xyToId(r, c));
                }
            }
            System.out.println("-------------------------------------------------------------------------------------");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row, col);
        if (percolationGrid[row][col]) {
            return;
        }
        percolationGrid[row][col] = true;
        numberOfOpenedSites++;
        bfs(row, col);
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

    /*public void bfs (int r, int c) {
        Queue<String> q = new LinkedList<>();
        q.add(r + "," + c);
        visited.add(r + "," + c);

        while (!q.isEmpty()) {
            String[] qElement = q.remove().split(",");
            int row = Integer.parseInt(qElement[0]);
            int col = Integer.parseInt(qElement[1]);

            int[][] directions = {{ 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }};

            for (int dr = 0; dr < directions.length; dr++) {
                for (int dc = 0; dc < directions[0].length; dc++) {
                   r = row + directions[dr][0];
                   c = col + directions[dr][1];

                   if ((r >= 0 && r < rows) && (c >= 0 && c < cols) && grid[r][c] == '1' && !visited.contains(r + "," + c)) {
                       q.add(r + "," + c);
                       visited.add(r + "," + c);
                   }
                }
            }
        }
    }

    public int numberOfIslands(char[][] islandsGrid) {
        grid = islandsGrid;

        if (grid.length == 0) {
            return 0;
        }

        rows = grid.length;
        cols = grid[0].length;

        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1' && !visited.contains(r + "," + c)) {
                    bfs(r, c);
                    islands++;
                }
            }
        }

        return islands;
    }*/

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
