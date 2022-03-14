import java.util.Arrays;

public class QuickFind {
  private int ids[];

  public QuickFind(int n) {
    ids = new int[n];
    for (int i = 0; i < n; i++) {
      ids[i] = i;
    }
  }

  public void union(int p, int q) {
    int pid = ids[p];
    int qid = ids[q];

    for (int i = 0; i < ids.length; i++) {
      if (ids[i] == pid) {
        ids[i] = qid;
      }
    }
  }

  public boolean connected(int p, int q) {
    return ids[p] == ids[q];
  }

  public String toString() {
    return Arrays.toString(ids);
  }

  public static void main(String[] args) {
    QuickFind qf = new QuickFind(10);
    int[] unionPairs = {
        0, 6,
        5, 6,
        1, 2,
        2, 7,
        3, 8,
        4, 9,
        3, 9,
        6, 1
    };

    for (int i = 0; i < unionPairs.length; i += 2) {
      int p = unionPairs[i];
      int q = unionPairs[i + 1];

      if (!qf.connected(p, q)) {
        qf.union(p, q);
      }
    }

    System.out.println(qf);
  }
}
