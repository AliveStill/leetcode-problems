package alivestill.whatever.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class C {
    public static double generate(int base, int exp) {
        if (base < 0 || exp < 0 || base < exp) {
            System.err.println("invalid! base= " + base + ", exp= " + exp);
        }
        return A.generate(base, exp) / A.generate(exp, exp);
    }
}

class CTest {

    public static void main(String[] args) {
        System.out.println(C.generate(5, 3));
        final int n = 10000;
        // int z = n - x  - y;
        List<Double> list = new ArrayList<>();
        for (int x = 0; x <= n; ++ x) {
            for (int y = 0; x + y <= n; ++ y) {
                // System.out.println("x: " + x + ",y: " + y);
                double kind = C.generate(n, x) * C.generate(n - x, y);
                list.add(kind);
            }
        }
        Collections.sort(list);
        System.out.println(list.get(list.size() - 1)); // find the last possible number
    }
}
