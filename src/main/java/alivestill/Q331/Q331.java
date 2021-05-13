package alivestill.Q331;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q331 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        String str = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(so.isValidSerialization(str));
    }
}

class Solution {
    public boolean isValidSerialization(String preorder) {
        String[] array = preorder.split(",");
        int lens = array.length;
        if (lens < 3) {
            return false;
        }
        if (array[0].equals("#")) {
            return false;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);   // 0 - no child, 1 - has left child
        int i = 1;
        for (; i < array.length; ++ i) {
            if (array[i].equals("#")) {
                if (stack.isEmpty()) {
                    return false;
                } // else
                if (stack.getLast() == 1) {
                    while (!stack.isEmpty() && stack.getLast() == 1) {
                        stack.removeLast();
                    }
                    if (stack.isEmpty()) {
                        ++ i;
                        break;
                    }
                    // alter 0 at stack top to 1
                    stack.removeLast(); // may merge, keep them for clearness
                    stack.addLast(1);
                } else {
                    stack.removeLast();
                    stack.addLast(1);
                }
            } else {
                stack.addLast(0);
            }
        }
        return i == lens && stack.isEmpty();
    }
}
