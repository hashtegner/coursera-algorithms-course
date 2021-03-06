import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final WeightedQuickUnionUF unionFinder;
  private final WeightedQuickUnionUF unionFinderFullPath;
  private final int gridSize;
  private final int virtualTop;
  private final int virtualBottom;
  private final boolean[][] grid;
  private int openSites;

  public Percolation(int gridSize) {
    if (gridSize < 1) {
      throw new IllegalArgumentException("gridSize must be >= 1 " + gridSize);
    }

    this.gridSize = gridSize;
    this.grid = new boolean[gridSize][gridSize];

    int squaredGrid = gridSize * gridSize;
    this.unionFinder = new WeightedQuickUnionUF(squaredGrid + 2); // virtual sizes
    this.unionFinderFullPath = new WeightedQuickUnionUF(squaredGrid + 1);
    this.virtualTop = squaredGrid;
    this.virtualBottom = squaredGrid + 1;

    this.openSites = 0;
  }

  public void open(int row, int col) {
    validatePosition(row, col);

    if (isOpen(row, col)) {
      return;
    }

    this.grid[row - 1][col - 1] = true;
    openSites++;

    int currentIndex = positionToListIndex(row, col);
    int left = col - 1;
    int right = col + 1;
    int top = row - 1;
    int bottom = row + 1;

    if (row == 1) {
      this.unionFinder.union(currentIndex, virtualTop);
      this.unionFinderFullPath.union(currentIndex, virtualTop);
    }

    if (row == gridSize) {
      this.unionFinder.union(currentIndex, virtualBottom);
    }

    if (!isOutOfBounds(row, left) && isOpen(row, left)) {
      int leftIndex = positionToListIndex(row, left);
      this.unionFinder.union(currentIndex, leftIndex);
      this.unionFinderFullPath.union(currentIndex, leftIndex);
    }

    if (!isOutOfBounds(row, right) && isOpen(row, right)) {
      int rightIndex = positionToListIndex(row, right);
      this.unionFinder.union(currentIndex, rightIndex);
      this.unionFinderFullPath.union(currentIndex, rightIndex);
    }

    if (!isOutOfBounds(top, col) && isOpen(top, col)) {
      int topIndex = positionToListIndex(top, col);
      this.unionFinder.union(currentIndex, topIndex);
      this.unionFinderFullPath.union(currentIndex, topIndex);
    }

    if (!isOutOfBounds(bottom, col) && isOpen(bottom, col)) {
      int bottomIndex = positionToListIndex(bottom, col);
      this.unionFinder.union(currentIndex, bottomIndex);
      this.unionFinderFullPath.union(currentIndex, bottomIndex);
    }
  }

  public boolean isOpen(int row, int col) {
    validatePosition(row, col);
    return grid[row - 1][col - 1];
  }

  public boolean isFull(int row, int col) {
    validatePosition(row, col);

    int index = positionToListIndex(row, col);
    return this.unionFinderFullPath.find(virtualTop) == this.unionFinderFullPath.find(index);
  }

  public int numberOfOpenSites() {
    return openSites;
  }

  public boolean percolates() {
    return this.unionFinder.find(virtualTop) == this.unionFinder.find(virtualBottom);
  }

  private boolean isOutOfBounds(int row, int col) {
    return row > gridSize || col > gridSize || row < 1 || col < 1;
  }

  private int positionToListIndex(int row, int col) {
    if (isOutOfBounds(row, col)) {
      throw new IndexOutOfBoundsException("invalid index for row and/or column (" + row + ", " + col + ", " + gridSize + ")");
    }

    return (row - 1) * gridSize + (col - 1);
  }

  private void validatePosition(int row, int col) {
    if (isOutOfBounds(row, col)) {
      throw new IllegalArgumentException("row and/or column is invalid (" + row + ", " + col + ")");
    }
  }
}
