package alivestill.Q187;

import java.util.*;

public class Q187 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        for (String str : so.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT")) {
            System.out.println(str);
        }
        for (String str : so.findRepeatedDnaSequences("AAAAAAAAAAAA")) {
            System.out.println(str);
        }
    }
}


class Solution {
    /// @brief brute-force
    public List<String> findRepeatedDnaSequences(String s) {
        int lens = s.length();
        if (lens <= 10) {
            return new ArrayList<String>();
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i + 10 < lens; ++ i) {
            String str = s.substring(i, i + 10);
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        List<String> list = new ArrayList<>();
        for (String str : map.keySet()) {
            if (map.get(str) >= 2) {
                list.add(str);
            }
        }
        return list;
    }
}