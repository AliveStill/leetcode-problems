package alivestill.Q780;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Q780 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        // TLE test case
        // 35
        //13
        //455955547
        //420098884
        long begin = System.currentTimeMillis();
        System.out.println(so.reachingPoints(35, 13, 455955547, 420098884));
        System.out.println((System.currentTimeMillis() - begin));
    }

    @Test
    @DisplayName("test1")
    public void test1() {
        long begin = System.currentTimeMillis();
        System.out.println(so.reachingPoints(1, 1, 3, 5));
        System.out.println((System.currentTimeMillis() - begin));
    }

    @Test
    @DisplayName("test2")
    public void test2() {
        long begin = System.currentTimeMillis();
        System.out.println(so.reachingPoints(1, 1, 2, 2));
        System.out.println((System.currentTimeMillis() - begin));
    }

    // 1
    //15
    //999999991
    //15
    @Test
    @DisplayName("test3")
    public void test3() {
        long begin = System.currentTimeMillis();
        System.out.println(so.reachingPoints(1, 15, 999999991, 15));
        System.out.println((System.currentTimeMillis() - begin));
    }

    // 3 3 12 9
    @Test
    @DisplayName("test4")
    public void test4() {
        long begin = System.currentTimeMillis();
        System.out.println(so.reachingPoints(3, 3, 12, 9));
        System.out.println((System.currentTimeMillis() - begin));
    }
}

// TLE
class SolutionV2 {
    /**
     * dfs
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        Set<Date> set = new HashSet<>();
        Deque<Date> stack = new ArrayDeque<>();
        long x = sx, y = sy;
        stack.add(new Date(x, y));
        while (!stack.isEmpty()) {
            Date num = stack.removeLast();
            if (set.contains(num)) {
                continue;
            }
            set.add(num);
            x = num.getX();
            y = num.getY();
            if (x == tx && y == ty) {
                return true;
            }
//            if (x > tx || y > ty) {
//                continue;
//            }   // included in later code
            if (x + y <= tx && y <= ty) {
                stack.addLast(new Date(x + y, y));
            }
            if (x + y <= ty && x <= tx) {
                stack.addLast(new Date(x, x + y));
            }
        }
        return false;
    }

    class Date {
        long x, y;

        public Date(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public void setX(long x) {
            this.x = x;
        }

        public long getY() {
            return y;
        }

        public void setY(long y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Date date = (Date) o;
            return x == date.x && y == date.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

// TLE
class SolutionV3 {
    /**
     * bidirectional dfs
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        }

        Set<Date> forwardSet = new HashSet<>();
        Set<Date> backwardSet = new HashSet<>();
        Deque<Date> forwardStack = new ArrayDeque<>();
        Deque<Date> backwardStack = new ArrayDeque<>();
        long x = sx, y = sy;
        forwardStack.add(new Date(x, y));
        backwardStack.add(new Date(tx, ty));
        while (!forwardStack.isEmpty() || !backwardStack.isEmpty()) {
            if (!forwardStack.isEmpty()) {
                // forward search
                Date num = forwardStack.removeLast();
                if (backwardSet.contains(num)) {
                    return true;
                }
                if (forwardSet.contains(num)) {
                    continue;
                }
                forwardSet.add(num);
                x = num.getX();
                y = num.getY();
//            if (x > tx || y > ty) {
//                continue;
//            }   // included in later code
                if (x + y <= tx && y <= ty) {
                    forwardStack.addLast(new Date(x + y, y));
                }
                if (x + y <= ty && x <= tx) {
                    forwardStack.addLast(new Date(x, x + y));
                }
            }

            if (!backwardStack.isEmpty()) {
                // backward search
                Date num = backwardStack.removeLast();
                if (forwardSet.contains(num)) {
                    return true;
                }
                if (backwardSet.contains(num)) {
                    continue;
                }
                backwardSet.add(num);
                x = num.getX();
                y = num.getY();
                if (x < 0 || y < 0) {
                    continue;
                }
                if (x > y) {
                    backwardStack.addLast(new Date(x - y, y));
                }
                else if (y > x) {
                    backwardStack.addLast(new Date(x, y - x));
                }
            }
        }
        return false;
    }

    class Date {
        long x, y;

        public Date(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public void setX(long x) {
            this.x = x;
        }

        public long getY() {
            return y;
        }

        public void setY(long y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Date date = (Date) o;
            return x == date.x && y == date.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

class Solution {
    /**
     * top-down is quite simple & intuitive, bottom-up TLE
     * a lot of details needed to be taken care of, really.
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        }
        if (tx == ty) { // never occur except that sx == tx, sy == ty
            return false;
        }
        int x = tx;
        int y = ty;
        while (x >= sx && y >= sy) {
            if (x == sx && y == sy) {
                return true;
            } else if (x == y) {
                return false;
            } else if (x > y) {
                if (y == sy && (x - sx) % y == 0) {
                    return true;
                } else {
                    x %= y;
                }
            } else {
                if (x == sx && (y - sy) % x == 0) {
                    return true;
                } else {
                    y %= x;
                }
            }
        }
        return false;
    }
}
