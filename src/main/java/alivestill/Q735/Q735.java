package alivestill.Q735;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q735 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        for (int i : so.asteroidCollision(new int[]{5, 10, -5})) {
            System.out.println(i);

        }
    }
}

class Solution {
    /**
     * naive merge
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        int lens = asteroids.length;
        boolean[] exploded = new boolean[lens];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int pos = 0;
        while (pos < lens) {
            // leftwards
            if (stack.isEmpty()) {
                if (asteroids[pos] > 0) {
                    stack.addLast(pos);
                }
                ++ pos;
            } else if (asteroids[pos] > 0) {
                stack.addLast(pos);
                ++ pos;
            } else {
                if (asteroids[stack.peekLast()] > -asteroids[pos]) {
                    exploded[pos] = true;
                    ++ pos;
                } else {
                    exploded[stack.removeLast()] = true;
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < lens; ++ i) {
            if (!exploded[i]) {
                list.add(asteroids[i]);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}