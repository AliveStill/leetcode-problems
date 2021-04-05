package alivestill.Q1172;

import java.util.*;

public class Q1172 {

    public static void main(String[] args) {
        // ["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
        // [[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
        // expected:
        //  [null,null,null,null,null,null,2,null,null,20,21,4,5,3,1,-1]
        DinnerPlates plates = new DinnerPlates(2);
        plates.push(1);
        plates.push(2);
        plates.push(3);
        plates.push(4);
        plates.push(5);
        System.out.println(plates.popAtStack(0));   // 2
        plates.push(20);
        plates.push(21);
        System.out.println(plates.popAtStack(0));   // 20
        System.out.println(plates.popAtStack(2));   // 21
        System.out.println(plates.pop());   // 5
        System.out.println(plates.pop());   // 4
        System.out.println(plates.pop());   // 3
        System.out.println(plates.pop());   // 1
        System.out.println(plates.pop());   // -1
    }
}

class DinnerPlates {

    int capacity;
    List<Deque<Integer>> stacks = new ArrayList<>(100000);
    TreeSet<Integer> fullStacks = new TreeSet<>(); // record those full stacks
    TreeSet<Integer> emptyStacks = new TreeSet<>();  // record those empty stacks
    TreeSet<Integer> notFullNotEmpty = new TreeSet<>(); // record the rest stacks
    {
        for (int i = 0; i < 100000; ++ i) {
            stacks.add(new ArrayDeque<>());
            emptyStacks.add(i);
        }
    }

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
    }

    public void push(int val) {
        // find the leftmost stack that's not full
        // then manipulate
        int emptyIndex = emptyStacks.isEmpty() ? Integer.MAX_VALUE : emptyStacks.first();
        int nfneIndex = notFullNotEmpty.isEmpty() ? Integer.MAX_VALUE : notFullNotEmpty.first();
        if (emptyIndex < nfneIndex) {
            // add to an empty stack
            stacks.get(emptyIndex).addLast(val);
            emptyStacks.remove(emptyIndex);
            if (capacity == 1) {
                fullStacks.add(emptyIndex);
            } else {
                notFullNotEmpty.add(emptyIndex);
            }
        } else {
            // add to an not-full-not-empty stack
            stacks.get(nfneIndex).addLast(val);
            if (stacks.get(nfneIndex).size() == capacity) {
                notFullNotEmpty.remove(nfneIndex);
                fullStacks.add(nfneIndex);
            }
        }
    }

    public int pop() {
        if (emptyStacks.size() == 100000) {
            return -1;
        } else {
            // find the rightmost stack that's not empty
            // then manipulate
            int fullIndex = fullStacks.isEmpty() ? Integer.MIN_VALUE : fullStacks.last();
            int nfneIndex = notFullNotEmpty.isEmpty() ? Integer.MIN_VALUE : notFullNotEmpty.last();
            if (fullIndex > nfneIndex) {
                int ret = stacks.get(fullIndex).removeLast();
                fullStacks.remove(fullIndex);
                if (stacks.get(fullIndex).isEmpty()) {
                    emptyStacks.add(fullIndex);
                } else {
                    notFullNotEmpty.add(fullIndex);
                }
                return ret;
            } else {
                int ret = stacks.get(nfneIndex).removeLast();
                if (stacks.get(nfneIndex).isEmpty()) {
                    notFullNotEmpty.remove(nfneIndex);
                    emptyStacks.add(nfneIndex);
                }
                return ret;
            }
        }
    }

    public int popAtStack(int index) {
        // do some modifications
        if (stacks.get(index).isEmpty()) {
            return -1;
        } else {
            int ret = stacks.get(index).removeLast();
            fullStacks.remove(index);
            if (stacks.get(index).isEmpty()) {
                notFullNotEmpty.remove(index);
                emptyStacks.add(index);
            } else {
                notFullNotEmpty.add(index);
            }
            return ret;
        }
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
