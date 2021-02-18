package alivestill.Q336;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q336 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        String[] words = new String[] { "abcd", "dcba", "lls", "s", "sssll"};
        for (List<Integer> list : so.palindromePairs(words)) {
            for (Integer integer : list) {
                System.out.print(integer + ",");
            }
            System.out.println();
        }

    }
}

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> list = new ArrayList<>();
        int lens = words.length;
        for (int i = 0; i < lens; ++ i) {
            for (int j = 0; j < lens; ++ j) {
                if (i != j) {
                    if (words[j].length() == words[i].length() - 1) {
                        if (new StringBuffer(words[i].substring(0, words[i].length() - 1)).reverse().toString().equals(words[j])) {
                            list.add(Arrays.asList(i, j));
                        }
                    } else if (words[j].length() == words[i].length()) {
                        if (new StringBuffer(words[i]).reverse().toString().equals(words[j])) {
                            list.add(Arrays.asList(i, j));
                        }
                    } else if (words[j].length() == words[i].length() + 1) {
                        if (new StringBuffer(words[j].substring(0, words[j].length() - 1)).reverse().toString().equals(words[i])) {
                            list.add(Arrays.asList(i, j));
                        }
                    }
                }
            }
        }
        return list;
    }
}
