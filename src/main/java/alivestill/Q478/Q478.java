package alivestill.Q478;

import java.util.Arrays;
import java.util.Random;

public class Q478 {
    public static void main(String[] args) {
        Solution so = new Solution(1, 0, 0);
        int count = 12306;
        while (count -- != 0) {
            System.out.println(Arrays.toString(so.randPoint()));
        }
    }
}

class Solution {

    /// @brief sample rejection
    public double[] randPoint() {
        Random random = new Random();
        double x, y;
        do {
            x = (random.nextDouble() - 0.5d) * 2 * radius;
            y = (random.nextDouble() - 0.5d) * 2 * radius;
        } while (x * x + y * y > radius * radius);
        return new double[]{x + xCenter, y + yCenter};
    }
    double radius;
    double xCenter;
    double yCenter;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xCenter = x_center;
        this.yCenter = y_center;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */
