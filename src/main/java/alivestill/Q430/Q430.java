package alivestill.Q430;

import java.util.TreeMap;
public class Q430 {
}

// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};

class Solution {

    /// @brief naive recursion
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node sentinel = new Node();
        sentinel.next = head;
        recur(head, sentinel);
        head.prev = null;
        return head;
    }

    /// @brief give a head and return a tail in a recursion pattern
    Node recur(Node curNode, Node prevNode) {
        /// link
        prevNode.next = curNode;
        curNode.prev = prevNode;
        Node tail = curNode;
        while (tail.next != null || tail.child != null) {
            if (tail.child != null) {
                Node nextNode = tail.next;
                Node intermediate = recur(tail.child, tail);
                tail.child = null;
                intermediate.next = nextNode;
                if (nextNode == null) {
                    return intermediate;
                } else {
                    nextNode.prev = intermediate;
                    tail = nextNode;
                }
            } else {
                tail = tail.next;
            }
        }
        return tail;
    }
}
