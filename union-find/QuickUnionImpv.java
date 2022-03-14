import java.util.Arrays;

public class QuickUnionImpv {
  private int ids[];
  private int sizes[];

  public QuickUnionImpv(int n) {
    ids = new int[n];
    sizes = new int[n];
    for (int i = 0; i < n; i++) {
      ids[i] = i;
      sizes[i] = 1;
    }
  }

  public void union(int p, int q) {
    int pid = root(p);
    int qid = root(q);

    if (pid == qid) {
      return;
    }

    if (sizes[pid] < sizes[qid]) {
      ids[pid] = qid;
      sizes[qid] += sizes[pid];
    } else {
      ids[qid] = pid;
      sizes[pid] += sizes[qid];
    }
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
    return "tree: " + Arrays.toString(ids) + " sizes: " + Arrays.toString(sizes);
  }

  public static void main(String[] args) {
    QuickUnionImpv qf = new QuickUnionImpv(10);
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
