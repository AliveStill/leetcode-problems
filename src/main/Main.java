import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/// debug file

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = 0;
        count = scan.nextInt();
        for (int i = 0; i < count; ++ i) {
            int num = 0;
            num = scan.nextInt();
            int[] array = new int[num];
            for (int j = 0; j < num; ++ j) {
                array[j] = scan.nextInt();
            }
            Arrays.sort(array);
            System.out.println(process(array));
        }
    }

//    @Test
//    public void test1() {
//        System.out.println(process(new int[] {1, 2, 2, 2, 5, 6}));  // 5
//    }

//
//    @Test
//    public void test2() {
//        System.out.println(process(new int[] {4, 4}));
//    }

//    @Test
//    public void test3() {
//        System.out.println(process(new int[] {1,1, 2, 2, 3, 3, 5, 6, 7}));
//        System.out.println(process(new int[] {1, 2, 3, 5, 6, 7}));
//    }

    private static int process(int[] array) {
        int lens = array.length;
        if (lens == 1) {
            return 1;
        }
        int ret = 1;    // array[0]
        Set<Integer> set = new HashSet<>();
        set.add(array[0]);
        for (int i = 1; i < lens; ++ i) {
            if (set.contains(array[i])) {
                if (!set.contains(array[i] + 1)) {
                    ++ ret;
                    set.add(array[i] + 1);
                }
            } else {
                ++ ret;
                set.add(array[i]);
            }
        }
        return ret;
    }
}
