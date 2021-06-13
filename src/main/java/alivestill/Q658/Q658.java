package alivestill.Q658;

import java.util.*;
import java.util.stream.Collectors;

public class Q658 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        for (Integer ele : so.findClosestElements(
                new int[]{0,0,1,2,3,3,4,7,7,8},3, 5)) {
            System.out.print(ele + ", ");
        }

    }
}

class Solution {
    /**
     * naive
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lens = arr.length;
        PriorityQueue<Data> pq = new PriorityQueue<>();
        for (int i = 0; i < lens; ++ i) {
            pq.add(new Data(Math.abs(x - arr[i]), arr[i]));
        }
        List<Integer> list  = new ArrayList<>();
        for (int i = 0; i < k; ++ i) {
            list.add(pq.poll().value);
        }
        Collections.sort(list);
        return list;
    }

    class Data implements Comparable<Data> {
        int distance;
        int value;

        public Data(int distance, int value) {
            this.distance = distance;
            this.value = value;
        }

        @Override
        public int compareTo(Data o) {
            if (this.distance == o.distance) {
                return this.value - o.value;
            } else {
                return distance - o.distance;
            }
        }
    }
}
