import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Middle {
  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);

    int m = in.nextInt();
    // YOUR CODE GOES HERE.
    int a = in.nextInt();
    int b = in.nextInt();
    node startNode = new node();
    startNode.value = b;
    node middle = startNode;
    boolean isOdd = false;
    boolean left = false;
    boolean right = false;

    for (int i = 1; i < m; i++) {
      // System.out.println(startNode.value);
      //  System.out.println(middle.value);
      int ins = in.nextInt();
      if (ins == 0) {
        System.out.println(middle.value);
      } else {
        int add = in.nextInt();
        node newNode = new node();
        newNode.value = add;
        node.addNode(startNode, newNode);
        startNode = newNode;
        if (isOdd) {
          if (left) {
            if (add <= middle.value) {
              middle = startNode.findSmaller(middle);
            }
            left = false;
          }
          if (right) {
            if (add > middle.value) {
              middle = startNode.findBigger(middle);
            }
            right = false;
          }
          isOdd = false;
        } else {
          if (add <= middle.value) {
            left = true;
          } else {
            right = true;
          }
          isOdd = true;
        }
      }
    }

    out.close();
  }

  private static class node {
    int value;
    node parent = null;
    node leftNode = null;
    node rightNode = null;

    private static void rotate(node parent, node child) {
      if (parent.parent != null) {
        if (parent.parent.leftNode == parent) {
          parent.parent.leftNode = child;
        } else {
          parent.parent.rightNode = child;
        }
      }
      child.parent = parent.parent;
      parent.parent = child;
      if (parent.leftNode == child) {
        parent.leftNode = child.rightNode;
        if (child.rightNode != null) {
          child.rightNode.parent = parent;
        }
        child.rightNode = parent;
      } else {
        parent.rightNode = child.leftNode;
        if (child.leftNode != null) {
          child.leftNode.parent = parent;
        }
        child.leftNode = parent;
      }
    }

    private static void addNode(node startNode, node newNode) {
      int value = newNode.value;
      while (true) {
        if (value <= startNode.value) {
          if (startNode.leftNode == null) {
            startNode.leftNode = newNode;
            newNode.parent = startNode;
            break;
          } else {
            startNode = startNode.leftNode;
          }
        } else {
          if (startNode.rightNode == null) {
            startNode.rightNode = newNode;
            newNode.parent = startNode;
            break;
          } else {
            startNode = startNode.rightNode;
          }
        }
      }

      while (newNode.parent != null) {
        node parent = newNode.parent;
        node grandParent = newNode.parent.parent;
        if (grandParent == null) {
          rotate(parent, newNode);
        } else if (grandParent.leftNode == parent) {
          if (parent.leftNode == newNode) {
            rotate(grandParent, parent);
            rotate(parent, newNode);
          } else {
            rotate(parent, newNode);
            rotate(grandParent, newNode);
          }
        } else {
          if (parent.rightNode == newNode) {
            rotate(grandParent, parent);
            rotate(parent, newNode);
          } else {
            rotate(parent, newNode);
            rotate(grandParent, newNode);
          }
        }
      }
    }

    private node findBigger(node currentNode) {
      if (currentNode.rightNode != null) {
        node nextNode = currentNode.rightNode;
        while (nextNode.leftNode != null) {
          nextNode = nextNode.leftNode;
        }
        return nextNode;
      } else {
        node nextNode = currentNode.parent;
        while (nextNode != null) {
          if (nextNode.leftNode == currentNode) {
            return nextNode;
          } else {
            currentNode = nextNode;
            nextNode = nextNode.parent;
          }
        }
      }
      return null;
    }

    private node findSmaller(node currentNode) {
      if (currentNode.leftNode != null) {
        node nextNode = currentNode.leftNode;
        while (nextNode.rightNode != null) {
          nextNode = nextNode.rightNode;
        }
        return nextNode;
      } else {
        node nextNode = currentNode.parent;
        while (nextNode != null) {
          if (nextNode.rightNode == currentNode) {
            return nextNode;
          } else {
            currentNode = nextNode;
            nextNode = nextNode.parent;
          }
        }
      }
      return null;
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
