import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private static final double CONFIDENCE = 1.96;
  private final int gridSize;
  private final int trials;
  private final double[] results;

  public PercolationStats(int gridSize, int trials) {
    if (gridSize < 1 || trials < 1) {
      throw new IllegalArgumentException("invalid grid size and/or trials (" + gridSize + ", " + trials + ")");
    }
    this.gridSize = gridSize;
    this.trials = trials;
    this.results = new double[trials];

    simulate();
  }

  public static void main(String[] args) {
    int gridSize = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);

    PercolationStats stats = new PercolationStats(gridSize, trials);

    StdOut.println("mean                    = " + stats.mean());
    StdOut.println("stddev                  = " + stats.stddev());
    StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
  }

  private void simulate() {
    int squareGrid = gridSize * gridSize;

    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(gridSize);

      while (!percolation.percolates()) {
        int row = StdRandom.uniform(1, gridSize + 1);
        int col = StdRandom.uniform(1, gridSize + 1);

        percolation.open(row, col);
      }

      int openSites = percolation.numberOfOpenSites();
      double result = (double) openSites / squareGrid;
      results[i] = result;
    }
  }

  public double mean() {
    return StdStats.mean(results);
  }

  public double stddev() {
    return StdStats.stddev(results);
  }

  public double confidenceLo() {
    return mean() - (CONFIDENCE * stddev() / Math.sqrt(trials));
  }

  public double confidenceHi() {
    return mean() + (CONFIDENCE * stddev() / Math.sqrt(trials));
  }
}
