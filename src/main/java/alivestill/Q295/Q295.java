package alivestill.Q295;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import java.util.*;

public class Q295 {

    public static MedianFinder medianFinder = new MedianFinder();

    public static void main(String[] args) {
        // test case 1:
        // ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
        // [[],[1],[2],[],[3],[]]
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());

        // test case 2:
        // ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
        // [[],[6],[],[10],[],[2],[],[6],[],[5],[],[0],[],[6],[],[3],[],[1],[],[0],[],[0],[]]
        // correct answer:  [null,null,6.00000,null,8.00000,null,6.00000,null,6.00000,null,6.00000,null,5.50000,null,6.00000,null,5.50000,null,5.00000,null,4.00000,null,3.00000]
        // my answer:       [null,null,6.00000,null,8.00000,null,10.00000,null,6.00000,null,6.00000,null,5.00000,null,5.50000,null,6.00000,null,3.00000,null,2.00000,null,1.00000]
        // FIXMED, my answer was finally correct after some modification
        medianFinder = new MedianFinder();
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());  // 6
        medianFinder.addNum(10);
        System.out.println(medianFinder.findMedian());  // 8
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());  // 6
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());  // 6
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());  // 6
        medianFinder.addNum(0);
        System.out.println(medianFinder.findMedian());  // 5.5
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());  // 6
    }

    @Test
    @DisplayName("test1")
    @Disabled("test passed!")
    public void test1() {
        int TOTAL = 10000;
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        medianFinder = new MedianFinder();
        int count = 0;
        for (int i = 1; i <= TOTAL; ++ i) {
            Integer num = random.nextInt(TOTAL * 2);
            list.add(num);
            Collections.sort(list);
            medianFinder.addNum(num);
            if ((i & 1) == 1) { // odd
                if (!doubleApproximate((double)list.get(i / 2), medianFinder.findMedian())) {
                    System.out.println("not equal!");
                    ++ count;
                }
            } else {
                if (!doubleApproximate((double)list.get(i / 2) + list.get(i / 2 - 1), 2 * medianFinder.findMedian())) {
                    ++ count;
                    System.out.println("not equal!");
                }
            }
        }
        System.out.println("total : " + TOTAL + ", count : " + count);
    }

    public static boolean doubleApproximate(Double d1, Double d2) {
        if (d1 == null && d2 == null) {
            return true;
        } else if (d1 == null || d2 == null) {
            return false;
        }
        return Math.abs(d1 - d2) <= 1e-5;
    }

    @Test
    @DisplayName("test2")
    public void test2() {
        medianFinder = new MedianFinder();
        medianFinder.addNum(18628);
        medianFinder.addNum(17459);
        medianFinder.addNum(2455);
        medianFinder.addNum(11329);
        medianFinder.addNum(1952);
        medianFinder.addNum(17292);
        do {} while(false);
    }
}

/// @details two ordered sets, any elements in one set are strictly no
///     less than the elements in another one, however there can't be any
///     duplicates in Set in Java' Standard Library, we use TreeMap instead
class MedianFinder {

    TreeMap<Integer, Integer> bigMap = new TreeMap<>();
    TreeMap<Integer, Integer> littleMap = new TreeMap<>();
    int totalSize = 0;

    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {
        if ((getTotalSize() & 1) == 0) {   // even
            if (bigMap.isEmpty() /* totalSize() == 0 */
                    || num <= bigMap.firstEntry().getKey()) {
                littleMap.put(num, littleMap.getOrDefault(num, 0) + 1);
            } else {
                Map.Entry<Integer, Integer> entry = bigMap.firstEntry();
                if (entry.getValue() == 1) {
                    bigMap.remove(entry.getKey());
                } else {
                    bigMap.put(entry.getKey(), entry.getValue() - 1);
                }
                bigMap.put(num, bigMap.getOrDefault(num, 0) + 1);
                littleMap.put(entry.getKey(), littleMap.getOrDefault(entry.getKey(), 0) + 1);
            }
        } else {    // odd
            // insert into bigMap
            if (num >= littleMap.lastEntry().getKey()) {
                bigMap.put(num, bigMap.getOrDefault(num, 0) + 1);
            } else {
                Map.Entry<Integer, Integer> entry = littleMap.lastEntry();
                if (entry.getValue() == 1) {
                    littleMap.remove(entry.getKey());
                } else {
                    littleMap.put(entry.getKey(), entry.getValue() - 1);
                }
                littleMap.put(num, littleMap.getOrDefault(num, 0) + 1);
                bigMap.put(entry.getKey(), bigMap.getOrDefault(entry.getKey(), 0) + 1);
            }
        }
        ++ totalSize;
//        if (totalSize >= 2) {
//            System.out.print(littleMap.lastEntry().getKey() + "\t");
//            System.out.print(bigMap.firstEntry().getKey() + "\t");
//            if (littleMap.lastEntry().getKey() > bigMap.firstEntry().getKey()) {
//                System.out.println("invalid");
//            } else {
//                System.out.println("valid");
//            }
//        }
    }

    public double findMedian() {
        if ((getTotalSize() & 1) == 0) {   // even
            return (double)(littleMap.lastEntry().getKey() +
                    bigMap.firstEntry().getKey()) / 2;
        } else {    // odd
            return (double)littleMap.lastEntry().getKey();
        }
    }

    public int getTotalSize() {
        return totalSize;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
