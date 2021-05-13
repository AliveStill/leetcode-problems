package alivestill.Q1381;

import org.junit.Test;

import java.util.*;

public class Q1381 {

    public static void main(String[] args) {
        // expected:
        // -1, 229
        CustomStack cs = new CustomStack(30);
        System.out.println(cs.pop());
        cs.increment(3, 40);
        cs.push(30);
        cs.increment(4, 63);
        cs.increment(2, 79);
        cs.increment(5, 57);
        System.out.println(cs.pop());
        cs.increment(5, 32);
    }
    
    @Test
    public void testcase1() {
        CustomStack cs = new CustomStack(169);
        cs.increment(8, 26);
        cs.push(83);
        cs.increment(2, 94);
        System.out.println(cs.pop());
        System.out.println(cs.pop());   // from this on
        cs.increment(3, 1);
        System.out.println(cs.pop());
        System.out.println(cs.pop());
        cs.increment(1, 51);
        cs.increment(6, 75);
        System.out.println(cs.pop());
        System.out.println(cs.pop());
        System.out.println(cs.pop());
        cs.increment(6, 75);
        System.out.println(cs.pop());
        cs.increment(5, 88);
        cs.increment(9, 99);
        System.out.println(cs.pop());
        cs.increment(8, 50);
        cs.push(41);
        System.out.println(cs.pop());       // should be 41
                                            // check point
        System.out.println(cs.pop());
        cs.increment(9, 63);
        System.out.println(cs.pop());
        System.out.println(cs.pop());
        System.out.println(cs.pop());
        cs.push(69);
        System.out.println(cs.pop());
        cs.increment(10, 56);
        cs.push(59);
        System.out.println(cs.pop());
        cs.increment(5, 98);
        System.out.println(cs.pop());
        System.out.println(cs.pop());
        cs.push(71);
        cs.push(35);
        cs.increment(5, 90);
        System.out.println(cs.pop());
        System.out.println(cs.pop());   // should be 161 rather than 71
        System.out.println(cs.pop());
        System.out.println(cs.pop());
        cs.push(19);
        // TODO, hand write to calculate
    }
}


class CustomStack {

    // max capacity of the stack
    int capacity = 0;

    // stack to store data
    Deque<Integer> stack = new ArrayDeque<>();

    // list to store additional sum, key == k bottom numbers
    // value == additional factor
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public CustomStack(int maxSize) {
        capacity = maxSize;
        map.put(0, 0); // sentinel node
    }

    public void push(int x) {
        if (stack.size() != capacity) {
            stack.addLast(x);
        }
    }

    // pop up the element at stack top
    public int pop() {
        // TODO, the most complex part
        if (!stack.isEmpty()) {
            // also true when map.size() == 1, that is, the map
            // only has a sentinel element
            if (stack.size() > map.lastKey()) {
                // no additional factor
                return stack.removeLast();
            }
            int topKey = map.lastKey();
            int topValue = map.remove(topKey);
            int ret = stack.peekLast() + topValue;
            if (stack.size() > map.lastKey()) {
                // or add addition factor of next top element
                ret += map.get(map.lastKey());
            } else {
                map.put(topKey, topValue);  // put back
            }
            stack.removeLast();
            return ret;
        } else {
            return -1;
        }
    }

    public void increment(int k, int val) {
        // do nothing when stack is empty
        if (!stack.isEmpty()) {
            int key = Math.min(stack.size(), k);
            map.put(key, map.getOrDefault(key, 0) + val);
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */