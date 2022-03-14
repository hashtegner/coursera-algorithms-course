import java.util.Arrays;

public class QuickUnion {
  private int ids[];

  public QuickUnion(int n) {
    ids = new int[n];
    for (int i = 0; i < n; i++) {
      ids[i] = i;
    }
  }

  public void union(int p, int q) {
    int pid = root(p);
    int qid = root(q);

    ids[pid] = qid;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  private int root(int i) {
    while (i != ids[i]) {
      i = ids[i];
    }

    return i;
  }

  public String toString() {
    return Arrays.toString(ids);
  }

  public static void main(String[] args) {
    QuickUnion qf = new QuickUnion(10);
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
