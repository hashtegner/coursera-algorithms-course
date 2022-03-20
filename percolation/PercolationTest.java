import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PercolationTest {
  @Test
  void positionToListIndex() {
    assertAll("valid positions", () -> {
      Percolation pc = new Percolation(5);
      assertEquals(0, pc.positionToListIndex(1,1));
      assertEquals(9, pc.positionToListIndex(2,5));
      assertEquals(15, pc.positionToListIndex(4,1));
      assertEquals(19, pc.positionToListIndex(4,5));
      assertEquals(24, pc.positionToListIndex(5,5));

      pc = new Percolation(3);
      assertEquals(0, pc.positionToListIndex(1,1));
      assertEquals(5, pc.positionToListIndex(2,3));
      assertEquals(8, pc.positionToListIndex(3,3));
    });

    assertAll("invalid positions", () -> {
      assertThrows(IndexOutOfBoundsException.class, () -> {
        new Percolation(5).positionToListIndex(5, 6);
      });

      assertThrows(IndexOutOfBoundsException.class, () -> {
        new Percolation(5).positionToListIndex(6, 5);
      });

      assertThrows(IndexOutOfBoundsException.class, () -> {
        new Percolation(5).positionToListIndex(4, 6);
      });

      assertThrows(IndexOutOfBoundsException.class, () -> {
        new Percolation(5).positionToListIndex(1, 6);
      });

      assertThrows(IndexOutOfBoundsException.class, () -> {
        new Percolation(5).positionToListIndex(0,5);
      });

      assertThrows(IndexOutOfBoundsException.class, () -> {
        new Percolation(5).positionToListIndex(1,0);
      });

      assertThrows(IndexOutOfBoundsException.class, () -> {
        new Percolation(5).positionToListIndex(-1,5);
      });

      assertThrows(IndexOutOfBoundsException.class, () -> {
        new Percolation(5).positionToListIndex(1,-1);
      });
    });
  }

  @Test
  void open() {
    int gridSize = 5;
    Percolation pc = new Percolation(5);

    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        assertFalse(pc.isOpen(i + 1, j + 1));
      }
    }

    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        pc.open(i + 1, j +1 );
        assertTrue(pc.isOpen(i + 1, j + 1));
      }
    }
  }

  @Test
  void numberOfOpenSites() {
    int gridSize = 5;
    Percolation pc = new Percolation(gridSize);

    assertEquals(0, pc.numberOfOpenSites());

    pc.open(1,1);
    assertEquals(1, pc.numberOfOpenSites());

    pc.open(2,1);
    assertEquals(2, pc.numberOfOpenSites());

    pc.open(2,1);
    assertEquals(2, pc.numberOfOpenSites());

    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        pc.open(i + 1, j +1 );
      }
    }

    assertEquals(gridSize * gridSize, pc.numberOfOpenSites());
  }

  @Test
  void percolates() {
    int gridSize = 8;
    Percolation pc = new Percolation(gridSize);

    pc.open(1, 3);
    pc.open(8, 6);
    assertFalse(pc.percolates());

    pc.open(7, 6);
    pc.open(7, 7);
    pc.open(6, 7);
    pc.open(5, 7);
    pc.open(4, 7);
    assertFalse(pc.percolates());

    pc.open(3, 7);
    pc.open(2, 7);
    pc.open(2, 6);
    pc.open(2, 5);
    pc.open(2, 4);
    assertFalse(pc.percolates());

    pc.open(2, 3);
    assertTrue(pc.percolates());
  }

  @Test
  void isFull() {
    int gridSize = 8;
    Percolation pc = new Percolation(gridSize);

    pc.open(1, 3);
    pc.open(8, 6);
    assertFalse(pc.isFull(4, 7));

    pc.open(3, 7);
    pc.open(2, 7);
    pc.open(2, 6);
    pc.open(2, 5);
    pc.open(2, 4);
    pc.open(2, 3);
    assertFalse(pc.isFull(4, 7));

    pc.open(4, 7);
    assertTrue(pc.isFull(4,7));
  }
}
