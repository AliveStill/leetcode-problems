package alivestill.Q641;

import alivestill.utils.DoubleLinkedList.DoubleLinkedList;
import alivestill.utils.DoubleLinkedList.ListNode;

public class Q641 {
}

class MyCircularDeque extends DoubleLinkedList<Integer> {

    int capacity;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        capacity = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (getSize() == capacity) {
            return false;
        } else {
            insertAfter(new ListNode<>(value), getHead());
            return true;
        }
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (getSize() == capacity) {
            return false;
        } else {
            insertBefore(new ListNode<>(value), getTail());
            return true;
        }
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        } else {
            remove(getHead().getNext());
            return true;
        }
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        } else {
            remove(getTail().getPrev());
            return true;
        }
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        } else {
            return getHead().getNext().getValue();
        }
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        } else {
            return getTail().getPrev().getValue();
        }
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return getSize() == capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */

