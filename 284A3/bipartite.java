import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bipartite {

  private static boolean isBipartite(List<int[]> adjList, int[] colors, int[] nodeColors, Deque<Integer> stack,
      int node) {
    colors[node] = 1;
    nodeColors[node] = 1;
    stack.push(node);
    while (!stack.isEmpty()) {
      node = stack.peek();
      int[] neighbors = adjList.get(node);
      boolean allVisited = true;
      for (int number : neighbors) {
        if (colors[number] == 0) {
          colors[number] = 1;
          nodeColors[number] = nodeColors[node] * -1;
          stack.push(number);
          allVisited = false;
          break;
        } else {
          if (nodeColors[number] == nodeColors[node]) {
            return false;
          }
        }
      }
      if (allVisited) {
        colors[node] = 2;
        stack.pop();
      }
    }
    return true;
  }

  // YOUR CODE GOES HERE.

  public static void main(String[] args) throws NumberFormatException, IOException {
    FastScanner in = new FastScanner();
    // PrintWriter out = new PrintWriter(System.out);

    while (true) {
      String orderLine = in.nextLine().trim();
      if (orderLine == null || orderLine.equals("")) {
        break;
      }
      int order = Integer.parseInt(orderLine);
      if (order == 0)
        break;
      int[] colors = new int[order];
      int[] nodeColors = new int[order];
      List<int[]> adjList = new ArrayList<>();
      Deque<Integer> stack = new LinkedList<>();

      for (int i = 0; i < order; i++) {
        String[] line = in.nextLine().split(" ");
        int[] nums = new int[line.length];
        for (int j = 0; j < line.length; j++) {
          if (line[j].trim().equals("") || line[j] == null) {
            nums = new int[0];
            break;
          }
          nums[j] = Integer.parseInt(line[j]);
        }
        adjList.add(nums);
        colors[i] = 0;
        nodeColors[i] = 0;
      }

      for (int i = 0; i < order; i++) {
        if (colors[i] == 0) {
          if (!isBipartite(adjList, colors, nodeColors, stack, i)) {
            System.out.println("0");
            break;
          }
        }
        if (i == order - 1) {
          System.out.println("1");
        }
      }
    }

  }

  static class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    // String next() {
    // while (!st.hasMoreTokens())
    // try {
    // st = new StringTokenizer(br.readLine());
    // } catch (IOException e) {
    // }
    // return st.nextToken();
    // }

    public String nextLine() throws IOException {
      return br.readLine();
    }

    // int nextInt() {
    // return Integer.parseInt(next());
    // }
  }
}
