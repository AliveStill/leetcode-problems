package alivestill.Q836;

import java.util.HashSet;
import java.util.Set;

public class Q836 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        // [0,0,2,2]
        //[1,1,3,3]
        // return : true
        int[] rect1 = new int[] {0, 0, 2, 2};
        int[] rect2 = new int[] {1, 1, 3, 3};
        boolean ret = so.isRectangleOverlap(rect1, rect2);
        System.out.println(ret);
    }
}

class Solution {
    // 1.overlap
    // 2.a in b
    // 3.b in a
    //  or(a0, a3)   or (a2, a3)
    //  (x3, y3)     (x2, y2)
    //  +-----------+
    //  |           |
    //  |           |
    //  |           |
    //  +-----------+
    // (x1, y1)     (x4, y4)
    // (a0, a1)     or(a2, a1)
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int ax1 = rec1[0], ay1 = rec1[1];
        int ax2 = rec1[2], ay2 = rec1[3];
        int ax3 = ax1, ay3 = ay2;
        int ax4 = ax2, ay4 = ay1;
        int bx1 = rec2[0], by1 = rec2[1];
        int bx2 = rec2[2], by2 = rec2[3];
        int bx3 = bx1, by3 = by2;
        int bx4 = bx2, by4 = by1;
        // case1: a in b
        if (ax1 >= bx1 && ay1 >= by1 && ax2 <= bx2 && ay2 <= by2) {
            return true;
        }
        // case2: b in a
        if (bx1 >= ax1 && by1 >= ay1 && bx2 <= ax2 && by2 <= ay2) {
            return true;
        }
        // case3: overlap
        return isPointInRectangle(ax1, ay1, bx1, by1, bx2, by2, bx3, by3, bx4, by4) ||
                isPointInRectangle(ax2, ay2, bx1, by1, bx2, by2, bx3, by3, bx4, by4) ||
                isPointInRectangle(ax3, ay3, bx1, by1, bx2, by2, bx3, by3, bx4, by4) ||
                isPointInRectangle(ax4, ay4, bx1, by1, bx2, by2, bx3, by3, bx4, by4);
    }

    /**
     * return true if point in rectangle, false if point in boundary segment or outside
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x4
     * @param y4
     * @return
     */
    private boolean isPointInRectangle(int x, int y,
                                       int x1, int y1,
                                       int x2, int y2,
                                       int x3, int y3,
                                       int x4, int y4) {
        Set<Integer> unary = new HashSet<>();
        int side;
        side = calculateSide(x, y, x1, y1, x3, y3);
        if (side != 0) {
            unary.add(side > 0 ? 1 : -1);
        }
        side = calculateSide(x, y, x1, y1, x4, y4);
        if (side != 0) {
            unary.add(side > 0 ? 1 : -1);
        }
        side = calculateSide(x, y, x2, y2, x3, y3);
        if (side != 0) {
            unary.add(side > 0 ? 1 : -1);
        }
        side = calculateSide(x, y, x2, y2, x4, y4);
        if (side != 0) {
            unary.add(side > 0 ? 1 : -1);
        }
        return unary.size() != 1;
    }

    // cross product
    private int calculateSide(int x, int y,
                        int x1, int y1,
                        int x2, int y2) {
        // cases included
//        if (x == x1 && x == x2) {
//            return 0;
//        }
//        if (y == y1 && y == y2) {
//            return 0;
//        }
        int deltax1 = x1 - x, deltax2 = x2 - x;
        int deltay1 = y1 - y, deltay2 = y2 - y;
        return x1 * y2 - x2 * y1;
    }
}
