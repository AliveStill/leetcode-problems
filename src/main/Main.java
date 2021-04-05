import java.util.Arrays;
import java.util.Scanner;

public class whatever {
}


/// debug file

public class Main {
    public static void main() {
        Scanner scan = new Scanner(System.in);
        int count = 0;
        count = scan.nextInt();
        for (int i = 0; i < count; ++ i) {
            int num = 0;
            num = scan.nextInt();
            int[] array = new int[num];
            for (int j = 0; j < num; ++ j) {
                array[i] = scan.nextInt();
            }
            Arrays.sort(array);
            System.out.println(process(array));
        }
    }

    private static int process(int[] array) {
        int lens = array.length;
        if (lens == 1) {
            return 0;
        }
        int ret = 0;
        int bound = array[0] + 1;
        for (int i = 1; i < lens; ++ i) {
            if (array[i] > bound) {
                bound = array[i];
            } else {
                ret += bound - array[i];
                bound = bound + 1;
            }
        }
        return ret;
    }
}
