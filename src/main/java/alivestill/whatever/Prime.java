package alivestill.whatever;

public class Prime {

    public final int COUNT = 9999;

    public static void main(String[] args) {

        for (int i = 2; i < 9999; ++ i) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    private static boolean isPrime(int x) {
        for (int i = 2; i <= Math.sqrt(x); ++ i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
