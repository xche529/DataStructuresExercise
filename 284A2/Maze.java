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
   
      }else{
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
    // YOUR CODE GOES HERE.

    out.close();
  }

  private static boolean checkPath(node nodeA, node nodeB, int[] finished) {
    
    return false;
  }

  private static node createMaze(int n) {
    node startNode = new node();
    startNode.value = 0;
    for (int i = 1; i < n * n; i++) {
      node newNode = new node();
      newNode.value = i;
      if (i % n != 0) {
        newNode.leftNode = searchNode(i - 1, newNode, n);
      }
      if (i % n != n - 1) {
        newNode.rightNode = searchNode(i + 1, newNode, n);
      }
      if (i / n != 0) {
        newNode.upNode = searchNode(i - n, newNode, n);
      }
      if (i / n != n - 1) {
        newNode.downNode = searchNode(i + n, newNode, n);
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
