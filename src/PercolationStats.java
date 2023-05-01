import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int trialsCount;
    private final double[] resultGrid;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <=0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        int gridSize = n * n;
        resultGrid = new double[trials];
        trialsCount = trials;
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
            resultGrid[turns] = (double) percolation.numberOfOpenSites() / (gridSize);
            turns++;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return (StdStats.mean(resultGrid));
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(resultGrid);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trialsCount));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trialsCount));
    }

    // test client (see below)
    public static void main(String[] args) {
        int gridSize = 200;
        int trialCount = 100;

        if (args.length >= 2) {
            gridSize = Integer.parseInt(args[0]);
            trialCount = Integer.parseInt(args[1]);
        }
        PercolationStats percolationStats = new PercolationStats(gridSize, trialCount);

        String confidence = percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi();
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = " + "[" + confidence + "]");
    }

}
