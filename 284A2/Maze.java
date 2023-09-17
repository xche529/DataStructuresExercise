import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Maze {
  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);

    int n = in.nextInt();
    int m = in.nextInt();

    // YOUR CODE GOES HERE.
    int[] mazeArray = new int[n * n];
    for (int i = 0; i < n * n; i++) {
      mazeArray[i] = i;
    }
    boolean[] colonArray = new boolean[n * n];
    for (int i = 0; i < n * n; i++) {
      colonArray[i] = true;
    }
    boolean[] rowArray = new boolean[n * n];
    for (int i = 0; i < n * n; i++) {
      rowArray[i] = true;
    }

    for (int i = 0; i < m; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      if (!isAccessAble(mazeArray, a, b)) {
        if (a == b + 1) {
          colonArray[b] = false;
        } else if (a == b - 1) {
          colonArray[a] = false;
        } else if (b == a + n) {
          rowArray[a] = false;
        } else {
          rowArray[b] = false;
        }
        int temp = findSet(mazeArray, a);
        int target = findSet(mazeArray, b);
        mazeArray[temp] = target;
        mazeArray[a] = target;
      }
    }
    boolean countOdd = true;
    for (int i = 0; i < n * 2 + 1; i++) {
      if (countOdd) {
        System.out.print("+");
        countOdd = false;
      } else {
        System.out.print("-");
        countOdd = true;
      }
      
    }
    System.out.print("\n");
    for (int i = 0; i < n * 2; i++) {
      boolean innerCountOdd = true;
      if (countOdd) {
        for (int j = 0; j < n * 2 + 1; j++) {
          if (innerCountOdd) {
            System.out.print("+");
            innerCountOdd = false;
          } else {
            if (rowArray[(i / 2) * n + (j / 2)]) {
              System.out.print("-");
            } else {
              System.out.print(" ");
            }
            innerCountOdd = true;
          }
        }
        System.out.print("\n");
        countOdd = false;
      } else {
        System.out.print("|");
        for (int j = 0; j < n * 2; j++) {
          if (innerCountOdd) {
            System.out.print(" ");
            innerCountOdd = false;
          } else {
            if (colonArray[(i / 2) * n + (j / 2)]) {
              System.out.print("|");
            } else {
              System.out.print(" ");
            }
            innerCountOdd = true;
          }
        }
        System.out.print("\n");
        countOdd = true;
      }
    }
    out.close();
  }

  public static boolean isAccessAble(int[] mazeArray, int n, int m) {
    return findSet(mazeArray, n) == findSet(mazeArray, m);
  }

  public static int findSet(int[] mazeArray, int i) {
    if (mazeArray[i] == i) {
      return i;
    } else {
      int target = findSet(mazeArray, mazeArray[i]);
      mazeArray[i] = target;
      return target;
    }
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
