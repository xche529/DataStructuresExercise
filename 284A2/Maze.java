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
    node startNode = createMaze(n);
    for (int i = 0; i < m; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      node nodeA = searchNode(a, startNode, n);
      node nodeB = searchNode(b, startNode, n);
      int[] finished = new int[n * n];
      if (checkPath(nodeA, nodeB, finished)) {

      } else {
        if (nodeA.upNode == nodeB) {
          nodeA.up = true;
          nodeB.down = true;
        } else if (nodeA.downNode == nodeB) {
          nodeA.down = true;
          nodeB.up = true;
        } else if (nodeA.leftNode == nodeB) {
          nodeA.left = true;
          nodeB.right = true;
        } else if (nodeA.rightNode == nodeB) {
          nodeA.right = true;
          nodeB.left = true;
        }
      }
    }
    printMaze(startNode, n);
    // YOUR CODE GOES HERE.

    out.close();
  }

  private static void printMaze(node startNode, int n) {
    boolean shape = true;
    boolean shape2 = true;
    int currentIndex = 0;
    for (int i = 0; i < n * 2 + 1; i++) {
      for (int j = 0; j < n * 2 + 1; j++) {
        if (shape && shape2) {
          System.out.print("+");
          shape2 = false;
        } else if (shape) {
          if (currentIndex >= n * n - 1) {
            System.out.print("-");
          } else if (searchNode(currentIndex, startNode, n).up) {
            System.out.print(" ");
          } else {
            System.out.print("-");
          }
          shape2 = true;
        } else {
          if (shape2) {
            System.out.print(" ");
          } else if (currentIndex % n == n - 1) {
            System.out.print("|\n");
          } else if (searchNode(currentIndex, startNode, n).left) {
            System.out.print(" ");
          } else {
            System.out.print("|");
          }
          shape2 = true;
        }
      }
      if (shape) {
        shape = false;
      } else {
        shape = true;
        currentIndex++;
      }
    }
  }

  private static boolean checkPath(node nodeA, node nodeB, int[] finished) {
    if (nodeA.leftNode != null && nodeA.left) {
      if (nodeA.leftNode == nodeB) {
        return true;
      } else {
        if (checkPath(nodeA.leftNode, nodeB, finished)) {
          return true;
        }
      }
    }
    if (nodeA.rightNode != null && nodeA.right) {
      if (nodeA.rightNode == nodeB) {
        return true;
      } else {
        if (checkPath(nodeA.rightNode, nodeB, finished)) {
          return true;
        }
      }
    }
    if (nodeA.upNode != null && nodeA.up) {
      if (nodeA.upNode == nodeB) {
        return true;
      } else {
        if (checkPath(nodeA.upNode, nodeB, finished)) {
          return true;
        }
      }
    }
    if (nodeA.downNode != null && nodeA.down) {
      if (nodeA.downNode == nodeB) {
        return true;
      } else {
        if (checkPath(nodeA.downNode, nodeB, finished)) {
          return true;
        }
      }
    }
    return false;
  }

  private static node createMaze(int n) {
    node startNode = new node();
    startNode.value = 0;
    for (int i = 1; i < n * n; i++) {
      node newNode = new node();
      newNode.value = i;
      if (i % n != 0) {
        node leftNode = searchNode(i - 1, startNode, n);
        newNode.leftNode = leftNode;
        leftNode.rightNode = newNode;
      }
      if (i / n != 0) {
        node upNode = searchNode(i - n, startNode, n);
        newNode.upNode = upNode;
        upNode.downNode = newNode;
      }
    }
    return startNode;
  }

  private static node searchNode(int targetValue, node startNode, int n) {
    node currentNode = startNode;
    int currentRemainder = startNode.value % n;
    int targetRemainder = targetValue % n;
    int currentQuotient = startNode.value / n;
    int targetQuotient = targetValue / n;
    while (currentRemainder > targetRemainder) {
      currentNode = currentNode.leftNode;
      currentRemainder = currentNode.value % n;
    }
    while (currentRemainder < targetRemainder) {
      currentNode = currentNode.rightNode;
      currentRemainder = currentNode.value % n;
    }
    while (currentQuotient > targetQuotient) {
      currentNode = currentNode.upNode;
      currentQuotient = currentNode.value / n;
    }
    while (currentQuotient < targetQuotient) {
      currentNode = currentNode.downNode;
      currentQuotient = currentNode.value / n;
    }
    return currentNode;
  }

  public static class node {
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    node upNode = null;
    node downNode = null;
    node leftNode = null;
    node rightNode = null;
    int value;
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
