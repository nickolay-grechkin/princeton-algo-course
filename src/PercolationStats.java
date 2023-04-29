import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;

public class PercolationStats {
    int openedSites;
    int gridSize;
    double[] resultGrid;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        gridSize = n * n;
        resultGrid = new double[trials];
        int turns = 0;
        while (turns != trials) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);

                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            resultGrid[turns] = percolation.numberOfOpenSites();
            turns++;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return (StdStats.mean(resultGrid) / gridSize);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 1.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 1.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 1.0;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(20, 10);
        System.out.println(percolationStats.mean());
    }

}
