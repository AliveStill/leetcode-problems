package alivestill.whatever;

import java.util.ArrayList;
import java.util.List;

public class test_2021_4_4_13_49 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);
        System.out.println(list.stream().mapToInt(i -> i).average().getAsDouble());
    }
}
