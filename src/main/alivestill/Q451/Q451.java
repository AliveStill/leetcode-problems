package alivestill.Q451;

import java.util.*;

public class Q451 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.frequencySort("tree"));
    }
}

class Solution {

    class Data implements Comparable{
        char ch;
        int freq;

        public Data(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        @Override
        public int compareTo(Object o) {
            return ((Data)o).freq - this.freq;
        }

        @Override
        public String toString() {
            char[] chars = new char[freq];
            Arrays.fill(chars, ch);
            return new String(chars);
        }

    }

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++ i) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        List<Data> list = new ArrayList<>();
        for (Character ch : map.keySet()) {
            list.add(new Data(ch, map.get(ch)));
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (Data data : list) {
            sb.append(data);
        }
        return sb.toString();
    }
}