package alivestill.whatever.math;

public class A {
    public static double generate(int base, int exp) {
        if (base < 0 || exp < 0 || base < exp) {
            System.err.println("invalid! base= " + base + ", exp= " + exp);
        }
        double ret = 1;
        while (exp-- != 0) {
            ret *= base--;
        }
        return ret;
    }
}

class ATest {
    public static void main(String[] args) {
        System.out.println(A.generate(5, 1));
        System.out.println(A.generate(10000, 34));
        System.out.println(A.generate(34, 34));
        for (long i = 0; i < 100; ++ i) {
            System.out.println("i = " + i + ", A(i, i)=" + A.generate((int)i, (int)i));
        }
    }
}
