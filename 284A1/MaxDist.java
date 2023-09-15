import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MaxDist {

  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);

    int t = in.nextInt();
    for (int i = 0; i < t; i++) {
      int n = in.nextInt();
      int k = in.nextInt();

      int a[] = new int[n];
      for (int j = 0; j < n; j++) a[j] = in.nextInt();
      out.println(solve(a, k, n));
    }

    out.close();
  }

  public static int solve(int a[], int k, int n) {
    int bottomD = 1;
    int topD = (a[n - 1] - a[0]) / (k - 1);
    if (topD == 1) {
      return 1;
    }
    int currentD = (bottomD + topD) / 2;

    while (true) {
      boolean isValid = checkIsValid(a, k, currentD, n);
      boolean isNextValid = checkIsValid(a, k, currentD + 1, n);
      if (isValid && !isNextValid) {
        return currentD;
      }
      if (isValid) {
        bottomD = currentD;
        currentD = (bottomD + topD + 1) / 2;
      } else {
        topD = currentD;
        currentD = (bottomD + topD) / 2;
      }
    }
  }

  public static boolean checkIsValid(int a[], int k, int d, int n) {
    int currentIndex = 0;
    for (int i = 1; i < k; i++) {
      int index = currentIndex;
      while (a[index] - a[currentIndex] < d) {
        index++;
        if (index >= n) {
          return false;
        }
      }
      currentIndex = index;
    }
    return true;
  }

  static class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens())
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
        }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }
}
