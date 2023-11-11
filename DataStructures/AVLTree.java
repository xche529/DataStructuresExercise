import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AVLTree {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node();
        while (true) {
            String[] line = null;
            try {
                line = reader.readLine().split(" ");
            } catch (Exception e) {
                break;
            }
            if (line.length == 1) {
                break;
            }
            if (line[0].equals("0")) {
                int value = Integer.parseInt(line[1]);
                root = root.addNode(value, root);
            } else {
                int value = Integer.parseInt(line[1]);
                if (root.searchValue(value)) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            }
        }
    }

    public static class Node {
        public Node left = null;
        public Node right = null;
        public Node parent = null;
        public int value;
        public int height;

        public Node(int value) {
            this.value = value;
            this.height = 1;
        }

        public Node() {
            this.height = 1;
        }

        public boolean searchValue(int value) {
            if (this.value == value) {
                return true;
            } else if (this.value > value) {
                if (this.left == null) {
                    return false;
                } else {
                    return this.left.searchValue(value);
                }
            } else {
                if (this.right == null) {
                    return false;
                } else {
                    return this.right.searchValue(value);
                }
            }
        }

        public Node addNode(int value, Node rootNode) {
            if (value == this.value) {
                return rootNode;
            }
            if (value < this.value) {
                if (this.left == null) {
                    Node node = new Node(value);
                    this.left = node;
                    node.parent = this;
                    rootNode = fixTree(node);
                    return rootNode;
                } else {
                    rootNode = this.left.addNode(value, rootNode);
                }
            } else {
                if (this.right == null) {
                    Node node = new Node(value);
                    this.right = node;
                    node.parent = this;
                    rootNode = fixTree(node);
                    return rootNode;
                } else {
                    rootNode = this.right.addNode(value, rootNode);
                }
            }
            return rootNode;
        }

        public Node fixTree(Node node) {
            Node rootNode = node;
            while (node != null) {
                rootNode = node;
                node.height = updateHeight(node);
                int leftHeight = 0;
                int rightHeight = 0;
                if (node.left != null) {
                    leftHeight = node.left.height;
                }
                if (node.right != null) {
                    rightHeight = node.right.height;
                }
                if (rightHeight - leftHeight > 1) {
                    int rightLeftHeight = 0;
                    int rightRightHeight = 0;
                    if (node.right.left != null) {
                        rightLeftHeight = node.right.left.height;
                    }
                    if (node.right.right != null) {
                        rightRightHeight = node.right.right.height;
                    }
                    if (rightLeftHeight > rightRightHeight) {
                        Node rightLeft = node.right.left;
                        rotate(rightLeft);
                        rotate(rightLeft);
                    } else {
                        rotate(node.right);
                    }
                } else if (leftHeight - rightHeight > 1) {
                    int leftLeftHeight = 0;
                    int leftRightHeight = 0;
                    if (node.left.left != null) {
                        leftLeftHeight = node.left.left.height;
                    }
                    if (node.left.right != null) {
                        leftRightHeight = node.left.right.height;
                    }
                    if (leftLeftHeight >= leftRightHeight) {
                        rotate(node.left);
                    } else {
                        Node leftRight = node.left.right;
                        rotate(leftRight);
                        rotate(leftRight);
                    }
                }
                node = node.parent;
            }
            return rootNode;
        }

        public int updateHeight(Node node) {

            int height = 0;
            if (node == null) {
                return height;
            }
            if (node.left == null) {
                height = 1;
            } else {
                height = node.left.height + 1;
            }
            if (node.right == null) {
                return height;
            } else {
                if (node.right.height + 1 > height) {
                    height = node.right.height + 1;
                }
            }
            return height;
        }

        public void rotate(Node node) {

            if (node.parent == null) {
                return;
            }
            Node parentNode = node.parent;

            if (parentNode.parent != null) {
                if (parentNode.parent.left == parentNode) {
                    parentNode.parent.left = node;
                } else {
                    parentNode.parent.right = node;
                }
            }
            node.parent = parentNode.parent;
            parentNode.parent = node;

            if (parentNode.left == node) {
                parentNode.left = node.right;
                if (node.right != null) {
                    node.right.parent = parentNode;
                }
                node.right = parentNode;

            } else {
                parentNode.right = node.left;
                if (node.left != null) {
                    node.left.parent = parentNode;
                }
                node.left = parentNode;

            }

            parentNode.height = updateHeight(parentNode);
            node.height = updateHeight(node);
        }
    }
}