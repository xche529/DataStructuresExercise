import java.util.Arrays;

public class InsertionSort {
    // This is a local version of insertion sort, which prints out the array after each iteration
  public static void main(String[] args) {
    int a[] = {6, 7, 8, 5, 345, 3, 23, 1, 23};
    String s = Arrays.toString(localInsertionSort(a));
    System.out.println(s);
  }

  public static int[] localInsertionSort(int a[]) {
    for (int i = 1; i < a.length; i++) {
      int currentIndex = i;
      while (a[currentIndex] < a[currentIndex - 1]) {
        int temp = a[currentIndex];
        a[currentIndex] = a[currentIndex - 1];
        a[currentIndex - 1] = temp;
        currentIndex--;
        if (currentIndex == 0) {
          break;
        }
      }
      String s = Arrays.toString(a);
      System.out.println(s);
    }
    return a;
  }
}
