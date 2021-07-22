package alivestill.Q812;

import org.junit.jupiter.api.Test;

public class Q812 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        // [[0,0],[0,1],[1,0],[0,2],[2,0]]
        // return 2.0000
        double area = so.largestTriangleArea(new int[][] {
                {0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}
        });
        System.out.println(area);
    }

    @Test
    public void test1(){
        double area = so.largestTriangleArea(new int[][]
                {{35,-23},{-12,-48},{-34,-40},{21,-25},{-35,-44},{24,1},
                        {16,-9},{41,4},{-36,-49},{42,-49},{-37,-20},{-35,11},{-2,-36},
                        {18,21},{18,8},{-24,14},{-23,-11},{-8,44},{-19,-3},{0,-10},
                        {-21,-4},{23,18},{20,11},{-42,24},{6,-19}});
        System.out.println(area);
    }
}

class Solution {
    /**
     * math
     * @param points
     * @return
     */
    public double largestTriangleArea(int[][] points) {
        double area = 0;
        int lens = points.length;
        for (int i = 0; i < lens; ++ i) {
            for (int j = i + 1; j < lens; ++ j) {
                for (int k = j + 1; k < lens; ++ k) {
                    double x = Math.sqrt(
                            (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) +
                            (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]));
                    double y = Math.sqrt(
                            (points[i][0] - points[k][0]) * (points[i][0] - points[k][0]) +
                                    (points[i][1] - points[k][1]) * (points[i][1] - points[k][1]));
                    double z = Math.sqrt(
                            (points[k][0] - points[j][0]) * (points[k][0] - points[j][0]) +
                                    (points[k][1] - points[j][1]) * (points[k][1] - points[j][1]));
                    // Helen Formulae
                    //S=√p(p-a)(p-b)(p-c)
                    if (x + y <= z || x + z <= y || y + z <= x) {
                        continue;   // can't form a triangle
                    }
                    double p = (x + y + z) / 2;
                    double S = Math.sqrt(p * (p - x) * (p - y) * (p - z));
                    // System.out.printf("x = %f, y = %f, z = %f, S = %f\n", x, y, z, S);
                    area = Math.max(S, area);
                }
            }
        }
        return area;
    }
}