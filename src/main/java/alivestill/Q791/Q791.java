package alivestill.Q791;

import java.util.*;

public class Q791 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.customSortString("cba", "abcd"));
    }
}

class Solution {
    /**
     * naive
     * @param order
     * @param str
     * @return
     */
    public String customSortString(String order, String str) {
        if (order.length() <= 1) {
            return str;
        }
        Map<Integer, Character> orderMap = new HashMap<>();
        Map<Character, Integer> strMap = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < order.length(); ++ i) {
            orderMap.put(i, order.charAt(i));
            set.add(order.charAt(i));
        }
        for (int i = 0; i < str.length(); ++ i) {
            strMap.put(str.charAt(i), strMap.getOrDefault(str.charAt(i), 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.length(); ++ i) {
            if (strMap.containsKey(orderMap.get(i))) {
                char ch = orderMap.get(i);
                int n = strMap.get(ch);
                sb.append(String.join("", Collections.nCopies(n, String.valueOf(ch))));
            }
        }
        for (Character key : strMap.keySet()) {
            if (!set.contains(key)) {
                int n = strMap.get(key);
                sb.append(String.join("", Collections.nCopies(n, String.valueOf(key))));
            }
        }
        return sb.toString();
    }
}
