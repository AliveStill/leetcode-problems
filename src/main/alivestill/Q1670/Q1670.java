package alivestill.Q1670;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q1670 {

    public static void main(String[] args) {

        FrontMiddleBackQueue fmb = new FrontMiddleBackQueue();
        // Input:
        // ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
        // [[], [1], [2], [3], [4], [], [], [], [], []]
        // expected:
        //  [null, null, null, null, null, 1, 3, 4, 2, -1]
        fmb.pushFront(1);
        fmb.pushBack(2);
        fmb.pushMiddle(3);
        fmb.pushMiddle(4);
        System.out.println("checkpoint");
    }
}

// manipulate two deque
// average O(1) time complexity for all operations
// pushFront, pushBack, pushMiddle, popFront, popBack, popMiddle
class FrontMiddleBackQueue {

    // seen as a contiguous memory block
    //  back                                    front
    // left-back | left-front | right-back | right-front
    // left-last | left-first | right-last | right-first (deque model)
    // left always holds n / 2 elements (floor approximation)
    // right holds the rest elements, n is their total size
    // so right always have number of elements that's no smaller than left's
    Deque<Integer> left = new ArrayDeque<>();
    Deque<Integer> right = new ArrayDeque<>();

    public FrontMiddleBackQueue() {
        // left empty
    }

    public void pushFront(int val) {
        // do not merge for clarity
        if (left.size() == right.size()) {
            right.addFirst(val);
        } else {
            right.addFirst(val);
            left.addFirst(right.removeLast());
        }
    }

    public void pushMiddle(int val) {
        if (left.size() == right.size()) {
            right.addLast(val);
        } else {
            left.addFirst(right.removeLast());
            right.addLast(val);
        }
    }

    public void pushBack(int val) {
        // do not merge for clarity
        if (left.size() == right.size()) {
            left.addLast(val);
            right.addLast(left.removeFirst());
        } else {
            left.addLast(val);
        }
    }

    public int popFront() {
        if (right.isEmpty()) {
            return -1;
        } else {
            if (left.size() == right.size()) {
                right.addLast(left.removeFirst());
                return right.removeFirst();
            } else {
                return right.removeFirst();
            }
        }
    }

    // pop the front most element if there is a tie in two middle elements
    public int popMiddle() {
        if (left.size() < right.size()) {
            return right.removeLast();
        } else {
            if (right.isEmpty()) {  // both empty
                return -1;
            } else {
                int ret = right.removeLast();
                right.addLast(left.removeFirst());
                return ret;
            }
        }
    }

    public int popBack() {
        if (left.isEmpty()) {   // both empty
            if (right.isEmpty()) {
                return -1;
            } else {
                return right.removeLast();
            }
        } else {
            if (left.size() == right.size()) {
                return left.removeLast();
            } else {
                left.addFirst(right.removeLast());
                return left.removeLast();
            }
        }
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */
