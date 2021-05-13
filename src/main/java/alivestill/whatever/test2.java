package alivestill.whatever;

import java.util.*;

public class test2 {

    static class Data {
        long timeStamp;

        public Data(long timeStamp) {
            this.timeStamp = timeStamp;
        }

        @Override
        public String toString() {
            return String.valueOf(timeStamp);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        List<Data> list = new ArrayList<>();
        for (int i = 0; i < 10000; ++ i) {
            list.add(new Data(random.nextInt(100000)));
        }
        // natural order, ascending
        // reverse order, descending
        Collections.sort(list, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return (int)(o1.timeStamp - o2.timeStamp);
            }
        });
        int i = 1;
        for (Data data : list) {
            System.out.print(String.format("%8s ", data));
            while (i ++ % 20 == 0) {
                System.out.println(data);
            }
        }
    }
}
