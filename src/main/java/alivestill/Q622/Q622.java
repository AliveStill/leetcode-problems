package alivestill.Q622;

import alivestill.utils.DoubleLinkedList.DoubleLinkedList;
import alivestill.utils.DoubleLinkedList.ListNode;

public class Q622 {

    public static void main(String[] args) {
        // ["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]
        // [[3],[1],[2],[3],[4],[],[],[],[4],[]]
        MyCircularQueue queue = new MyCircularQueue(3);
        System.out.println(queue.enQueue(1));
        System.out.println(queue.enQueue(2));
        System.out.println(queue.enQueue(3));
        System.out.println(queue.enQueue(4));
        System.out.println(queue.Rear());
        System.out.println(queue.isFull());
        System.out.println(queue.deQueue());
        System.out.println(queue.enQueue(4));
        System.out.println(queue.Rear());
    }
}

/**
 * Double linked list
 */
class MyCircularQueue {

    private int compacity;
    // head AS front, front to pick
    // tail AS rear, rear to insert
    DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

    public MyCircularQueue(int k) {
        compacity = k;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        } else {
            ListNode<Integer> node = new ListNode<Integer>(value);
            // insert at rear
            list.insertBefore(node, list.getTail());
            return true;
        }
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        } else {
            list.remove(list.getHead().getNext());
            return true;
        }
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        } else {
            return list.getHead().getNext().getValue();
        }
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        } else {
            return list.getTail().getPrev().getValue();
        }
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public boolean isFull() {
        return getSize() == compacity;
    }

    public long getSize() {
        return list.getSize();
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */