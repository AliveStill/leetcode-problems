package alivestill.Q593;

import java.util.HashSet;
import java.util.Set;

public class Q593 {
}

class SolutionV2 {
    /// @brief naive
    /// FIXMED, invalid algorithm, just work for unbiased square
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // use p2 as base
        int[][] points = new int[4][2];
        System.arraycopy(p1, 0, points[0], 0, 2);
        System.arraycopy(p2, 0, points[1], 0, 2);
        System.arraycopy(p3, 0, points[2], 0, 2);
        System.arraycopy(p4, 0, points[3], 0, 2);
//       int p1x = p1[0]; int p1y = p1[1];
//       int p2x = p1[0]; int p2y = p1[1];
//       int p3x = p1[0]; int p3y = p1[1];
//       int p4x = p1[0]; int p4y = p1[1];
       int ver = 0, hor = 0, dia = 0, diaIndex = -1;
       Set<Integer> perimeters = new HashSet<>();
       // points[0] as base
       for (int i = 1; i < 4; ++ i) {
           if (points[i][0] == points[0][0]) {
               ++ ver;
               perimeters.add(Math.abs(points[0][1] - points[i][1]));
           } else if (points[i][1] == points[0][1]) {
               ++ hor;
               perimeters.add(Math.abs(points[0][0] - points[i][0]));
           } else {
               ++ dia;
               diaIndex = i;
           }
       }
       if (ver != 1 || hor != 1 || dia != 1
               || perimeters.size() != 1 || perimeters.contains(0)) {
           return false;
       }
       // reset, points[diaIndex] as another base
       ver = 0; hor = 0;
       for (int i = 1; i < 4; ++ i) {
           if (i != diaIndex) {
               if (points[i][0] == points[diaIndex][0]) {
                   ++ ver;
                   perimeters.add(Math.abs(points[diaIndex][1] - points[i][1]));
               } else if (points[i][1] == points[diaIndex][1]) {
                   ++ hor;
                   perimeters.add(Math.abs(points[diaIndex][0] - points[i][0]));
               }
           }
       }
       return ver == 1 && hor == 1 && perimeters.size() == 1
               && !perimeters.contains(0);
    }
}

class Solution {
    /// @brief mathematics
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int sop = Math.min(calculateSquareOfSegment(p1, p2), 
                calculateSquareOfSegment(p1, p3));
        Set<Integer> set = new HashSet<>();
        set.add(calculateSquareOfSegment(p1, p2));
        set.add(calculateSquareOfSegment(p1, p3));
        set.add(calculateSquareOfSegment(p1, p4));
        set.add(calculateSquareOfSegment(p2, p3));
        set.add(calculateSquareOfSegment(p2, p4));
        set.add(calculateSquareOfSegment(p3, p4));
        return set.size() == 2 && set.contains(2 * sop);
    }

    private int calculateSquareOfSegment(int[] p1, int []p2) {
        int deltax = p1[0] - p2[0];
        int deltay = p1[1] - p2[1];
        return deltax * deltax + deltay * deltay;
        // return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}