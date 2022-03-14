import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
  public static void main(String[] args) {
    String champion = "";
    double attempt = 0;

    while(!StdIn.isEmpty()) {
      String str = StdIn.readString();
      attempt++;

      if (StdRandom.bernoulli(1.0 / attempt)) {
        champion = str;
      }
    }

    System.out.println(champion);
  }
}
