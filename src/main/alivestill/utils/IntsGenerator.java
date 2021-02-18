package alivestill.utils;

import java.util.Random;

// useage: 1000 0 2500 false ,
public class IntsGenerator {

    static int totalNum = 200;
    static boolean isDelimiterEnabled = true;
    static final String defaultDelimiter = ",";
    static String realDelimiter = defaultDelimiter;
    static int upperBound = 100000;
    static int lowerBound = 0;

    public static void main(String[] args) {
        if (args.length >= 2) {
            totalNum = Integer.parseInt(args[1]);
        }
        if (args.length >= 3) {
            lowerBound = Integer.parseInt(args[2]);
        }
        if (args.length >= 4) {
            upperBound = Integer.parseInt(args[3]);
        }
        if (args.length >= 5) {
            isDelimiterEnabled = Boolean.parseBoolean(args[2]);
        }
        if (args.length >= 6) {
            realDelimiter = args[3];
        }
        Random random = new Random();
        for (int i = 1; i <= totalNum; ++ i) {
            System.out.print(String.format("%6d", Math.abs(random.nextInt() % (upperBound - lowerBound)) + lowerBound));
            if (isDelimiterEnabled && i != totalNum) {
                System.out.print(realDelimiter);
            }
            if (0 == i % 20) {
                System.out.println();
            }
        }
    }
}
