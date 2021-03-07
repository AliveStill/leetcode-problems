package alivestill.Q427;

public class Q427 {
}


// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}

class Solution {
    int[][] matrix;

    public Node construct(int[][] grid) {
        matrix = grid;
        return recur(0, 0, grid.length);
    }

    Node recur(int xpos, int ypos, int size) {
        if (size == 1) {
            return new Node(matrix[xpos][ypos] == 1, true);
        }
        Node node1 = recur(xpos, ypos, size / 2);
        Node node2 = recur(xpos, ypos + size / 2, size / 2);
        Node node3 = recur(xpos + size / 2, ypos, size / 2);
        Node node4 = recur(xpos + size / 2, ypos + size / 2, size / 2);
        Node node = new Node(false, false, node1, node2, node3, node4);
        if (node1.isLeaf && node2.isLeaf && node3.isLeaf && node4.isLeaf
                && node1.val == node2.val && node2.val == node3.val
                && node3.val == node4.val) {
            node.val = node1.val;
            node.topLeft = node.topRight = node.bottomLeft = node.bottomRight = null;
            node.isLeaf = true;
        }
        return node;
    }
}


