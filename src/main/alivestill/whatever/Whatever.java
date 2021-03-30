package alivestill.whatever;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.*;

public class Whatever {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        // should all be boxed types
        Integer[] array = set.toArray(new Integer[0]);
        System.out.println("Hello, world!");

        System.out.println(99999L * 9999L);
        System.out.println(Integer.valueOf(65).toBinaryString(65));
        for (int i = 1; i <= 26; ++ i) {
            System.out.printf("%8s", i + ":" + Character.valueOf((char)(i + 'a')).toString() + ", ");
            if (i % 10 == 0) System.out.println();
        }
    }

    @Test
    @DisplayName("ensure insertion point")
    public void ensureInsertionPoint() {
        int[] array = new int[]{1, 3, 6, 10};
        System.out.println(Arrays.binarySearch(array, 7));
    }

}
