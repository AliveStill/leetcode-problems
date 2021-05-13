package alivestill.whatever;

import java.util.ArrayList;
import java.util.List;

public class test_2021_4_1_12_12 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        List<Integer> cp = list.subList(0, 3);
        list = cp;
        // FIXME, List.sublist() returns a portion view, do not truncate the original list
        System.out.println(list);
        // that's the correct use
        list.subList(2, 3).clear();
        System.out.println(list);

        // throw exception, out of boundary
        list.subList(12, 15).clear();
    }
}
