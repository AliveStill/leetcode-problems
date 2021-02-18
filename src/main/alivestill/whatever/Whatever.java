package alivestill.whatever;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Whatever {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        // should all be boxed types
        Integer[] array = set.toArray(new Integer[0]);
        System.out.println("Hello, world!");

        System.out.println(99999L * 9999L);
    }
}
