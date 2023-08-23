import java.util.Arrays;

public class SelectionSort {

//This is a local version of selection sort, which prints out the array after each iteration
  public static void main(String[] args) {
    int a[] = {6, 7, 8, 5, 345, 3, 23, 1, 23};
    String s = Arrays.toString(localSelectionSort(a));
    System.out.println(s);
  }

  public static int[] localSelectionSort(int a[]) {
    for (int i = 0; i < a.length; i++) {
      int max = a[0];
      int maxIndex = 0;
      int temp = 0;
      String s = Arrays.toString(a);
      System.out.println(s);
      for (int j = 0; j < a.length - i; j++) {
        if (a[j] > max) {
          max = a[j];
          maxIndex = j;
        }
      }
      temp = a[a.length - i - 1];
      a[a.length - i - 1] = max;
      a[maxIndex] = temp;
    }
    return a;
  }

  public int[] selectionSort(int a[]) {
    for (int i = 0; i < a.length; i++) {
      int max = a[0];
      int maxIndex = 0;
      int temp = 0;
      for (int j = 0; j < a.length - i; j++) {
        if (a[j] > max) {
          max = a[j];
          maxIndex = j;
        }
      }
      temp = a[a.length - i - 1];
      a[a.length - i - 1] = max;
      a[maxIndex] = temp;
    }
    return a;
  }
}
