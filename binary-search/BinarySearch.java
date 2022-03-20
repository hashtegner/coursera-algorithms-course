public class BinarySearch {
  private final int[] items;

  public BinarySearch(int[] items) {
    this.items = items;
  }

  public static void main(String[] args) {
    BinarySearch bs = new BinarySearch(new int[]{
      1, 10, 20, 35, 40, 70, 128
    });

    System.out.println(1 + " " + bs.search(1));
    System.out.println(35 + " " + bs.search(35));
    System.out.println(70 + " " + bs.search(70));
    System.out.println(71 + " " + bs.search(71));
    System.out.println(128 + " " + bs.search(128));
    System.out.println(1000 + " " + bs.search(1000));
    System.out.println(-10 + " " + bs.search(-10));
  }

  public int search(int n) {
    int low = 0;
    int high = items.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (n < items[mid]) high = mid - 1;
      else if (n > items[mid]) low = mid + 1;
      else return mid;
    }

    return -1;
  }
}
