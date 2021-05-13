package alivestill.achaosfolder;

public class LoopPerformance1e10 {

    public static void main(String[] args) {
        int num = 0;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100000; ++ i) {
            for (int j = 0; j < 100000; ++ j) {
                ++ num;
            }
        }
        long end = System.currentTimeMillis();
        System.out.print(end - begin);
        System.out.println("ms");   // 5ms
        // NOTE, 5ms for 1e10 loops after loop optimization, since 1e12 just 9ms,
        //  that doesn't make sense if not
    }
}
