package alivestill.whatever;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

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


}
