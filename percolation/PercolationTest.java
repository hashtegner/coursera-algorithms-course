import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PercolationTest {
  @Test
  void constructor() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Percolation(0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Percolation(-1);
    });

    assertDoesNotThrow(() -> {
      new Percolation(1);
      new Percolation(10);
    });
  }

  @Test
  void open() {
    int gridSize = 5;
    Percolation pc = new Percolation(5);

    assertAll("valid positions", () -> {

      for (int i = 0; i < gridSize; i++) {
        for (int j = 0; j < gridSize; j++) {
          assertFalse(pc.isOpen(i + 1, j + 1));
        }
      }

      for (int i = 0; i < gridSize; i++) {
        for (int j = 0; j < gridSize; j++) {
          pc.open(i + 1, j + 1);
          assertTrue(pc.isOpen(i + 1, j + 1));
        }
      }

    });


    assertAll("invalid positions", () -> {
      assertThrows(IllegalArgumentException.class, () -> {
        pc.isOpen(0, 1);
      });

      assertThrows(IllegalArgumentException.class, () -> {
        pc.open(0, 1);
      });

      assertThrows(IllegalArgumentException.class, () -> {
        pc.isOpen(-1, 1);
      });

      assertThrows(IllegalArgumentException.class, () -> {
        pc.open(-1, 1);
      });

      assertThrows(IllegalArgumentException.class, () -> {
        pc.isOpen(6, 1);
      });

      assertThrows(IllegalArgumentException.class, () -> {
        pc.open(6, 1);
      });

      assertThrows(IllegalArgumentException.class, () -> {
        pc.isOpen(5, 6);
      });

      assertThrows(IllegalArgumentException.class, () -> {
        pc.open(5, 6);
      });
    });
  }

  @Test
  void numberOfOpenSites() {
    int gridSize = 5;
    Percolation pc = new Percolation(gridSize);

    assertEquals(0, pc.numberOfOpenSites());

    pc.open(1, 1);
    assertEquals(1, pc.numberOfOpenSites());

    pc.open(2, 1);
    assertEquals(2, pc.numberOfOpenSites());

    pc.open(2, 1);
    assertEquals(2, pc.numberOfOpenSites());

    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        pc.open(i + 1, j + 1);
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

    assertAll("valid position", () -> {


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
      assertTrue(pc.isFull(4, 7));

    });

    assertAll("invalid positions", () -> {
      assertThrows(IllegalArgumentException.class, () -> {
        pc.isFull(0, 1);
      });


      assertThrows(IllegalArgumentException.class, () -> {
        pc.isFull(-1, 1);
      });


      assertThrows(IllegalArgumentException.class, () -> {
        pc.isFull(9, 1);
      });


      assertThrows(IllegalArgumentException.class, () -> {
        pc.isFull(8, 9);
      });
    });
  }
}
