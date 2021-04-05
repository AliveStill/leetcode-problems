import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class Main2 {

//    static double[] aWinTable = new double[10001];
//    static double[] tieTable = new double[10001];
//    static int size = 0;
//
//    static {
//        tieTable[0] = 1d;   // never used
//        aWinTable[0] = 0;   // by default
//        for (int i = 25; i <= 1000; ++ i) {
//            double win = Awin(i, i);
//            double tie = tie(i, i);
//            for (int j = i; j > i - 25; ++ j) {
//                aWinTable[j] = win;
//                tieTable[j] = tie;
//            }
//            size += 25;
//        }
//    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count= 0;
        count = scan.nextInt();
        for (int i = 0; i < count; ++ i) {
            int num = 0;
            double ret = 0;
            num = scan.nextInt();
            System.out.println(Awin(i, i) + tie(i, i) / 2);
        }
    }

    @Test
    public void test() {
        for (int i = 1; i < 10000; ++ i) {
            System.out.println(String.format("i = %-6d", i) + String.valueOf(Awin(i, i) + tie(i, i) / 2));
        }
    }

    private static double Awin(double x, double y) {
        if (x <= 0) {
            if (y <= 0) {
                return 0d;
            } else {
                return 1d;
            }
        }
        if (y <= 0) {
            return 0d;
        }
        return 0.25 * Awin(x - 100, y) + 0.25 * Awin(x - 75, y - 25) +
                0.25 * Awin(x - 50, y - 50) + 0.25 * Awin(x - 25, y - 75);
    }

    private static double tie(double x, double y) {
        if (x <= 0 && y <= 0) {
            return 1d;
        }
        if (x <= 0) {
            return 0;
        }
        if (y <= 0) {
            return 0;
        }
        return 0.25 * tie(x - 100, y) + 0.25 * tie(x - 75, y - 25) +
                0.25 * tie(x - 50, y - 50) + 0.25 * tie(x - 25, y - 75);
    }
}
