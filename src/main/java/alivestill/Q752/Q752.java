package alivestill.Q752;

import org.junit.jupiter.api.Test;

import java.util.*;

public class Q752 {

    public static void main(String[] args) {
        Solution so = new Solution();
        int res = so.openLock(new String[] {
                "0201","0101","0102","1212","2002"
        }, "0202");
        System.out.println(res);
    }

    @Test
    public void leftShiftTest() {
        Solution so = new Solution();
        System.out.println("leftShift: ");
        for (char ch = '0'; ch <= '9'; ++ ch) {
            System.out.println(ch + " -> " + so.leftShift(ch));
        }
    }

    @Test
    public void rightShiftTest() {
        Solution so = new Solution();
        System.out.println("rightShift: ");
        for (char ch = '0'; ch <= '9'; ++ ch) {
            System.out.println(ch + " -> " + so.rightShift(ch));
        }
    }
}

class Solution {

    Set<String> deadends = new HashSet<>();
    Map<String, Integer> effort = new HashMap<>();
    Set<String> visited = new HashSet<>();

    /**
     * Dijkstra
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        Collections.addAll(this.deadends, deadends);
        if (this.deadends.contains("0000")) {
            return -1;
        }
        if (this.deadends.contains(target)) {
            return -1;
        }
        effort.put("0000", 0);
        Deque<StringBuilder> queue = new ArrayDeque<>();
        queue.addLast(new StringBuilder("0000"));
        while (!queue.isEmpty()) {
            StringBuilder sb = queue.removeFirst();
            String str = sb.toString();
            int dist = effort.get(str);
            if (this.deadends.contains(str)) {
                continue;
            }
            if (visited.contains(str)) {
                continue;
            }
            for (int i = 0; i < 4; ++ i) {
                StringBuilder sb1 = new StringBuilder(sb);
                sb1.setCharAt(i, leftShift(str.charAt(i)));
                String res1 = sb1.toString();
                effort.put(res1, Math.min(effort.getOrDefault(res1, Integer.MAX_VALUE), dist + 1));
                queue.addLast(sb1);

                StringBuilder sb2 = new StringBuilder(sb);
                sb2.setCharAt(i, rightShift(str.charAt(i)));
                String res2 = sb2.toString();
                effort.put(res2, Math.min(effort.getOrDefault(res2, Integer.MAX_VALUE), dist + 1));
                queue.addLast(sb2);
            }
            visited.add(str);
        }
//        for (String ele : effort.keySet()) {
//            System.out.println(ele + " : " + effort.get(ele));
//        }
        return effort.getOrDefault(target, -1);
    }

    public char leftShift(char ch) {
        return (char)(0x30 | ((ch - 1 - 0x30 + 10) % 10));
    }

    public char rightShift(char ch) {
        return (char)(0x30 | ((ch + 1 - 0x30 + 10) % 10));
    }
}
