package alivestill.Q707;

import alivestill.utils.DoubleLinkedList.DoubleLinkedList;
import alivestill.utils.DoubleLinkedList.ListNode;

public class Q707 {

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        // ["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
        // [[],[1],[3],[1,2],[1],[1],[1]]
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        System.out.println(list.get(1));
        list.deleteAtIndex(1);
        System.out.println(list.get(1));
    }
}

class MyLinkedList extends DoubleLinkedList<Integer> {

    /** Initialize your data structure here. */
    public MyLinkedList() {
        // left empty
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index >= getSize()) {
            return -1;
        } else {
            ListNode<Integer> node = getHead().getNext();
            for (int i = 0; i < index; ++ i) {
                node = node.getNext();
            }
            return node.getValue();
        }
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        insertAfter(new ListNode<>(val), getHead());
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        insertBefore(new ListNode<>(val), getTail());
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < getSize()) {
            ListNode<Integer> node = getHead().getNext();
            for (int i = 0; i < index; ++ i) {
                node = node.getNext();
            }
            insertBefore(new ListNode<>(val), node);
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < getSize()) {
            ListNode<Integer> node = getHead().getNext();
            for (int i = 0; i < index; ++ i) {
                node = node.getNext();
            }
            remove(node);
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */