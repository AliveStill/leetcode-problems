package alivestill.Q809;

import alivestill.utils.Pair.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Q809 {
}

class Solution {
    /**
     * naive
     * @param s
     * @param words
     * @return
     */
    public int expressiveWords(String s, String[] words) {
        List<Pair<Character, Integer>> list = formList(s);
        int ret = 0;
        loop1:
        for (String word : words) {
            List<Pair<Character, Integer>> cmp = formList(word);
            if (list.size() == cmp.size()) {
                for (int i = 0; i < list.size(); ++ i) {
                    if (list.get(i).getFirst() != cmp.get(i).getFirst()) {
                        continue loop1;
                    }
                    if (list.get(i).getSecond() < cmp.get(i).getSecond()) {
                        continue loop1;
                    }
                    if (!list.get(i).getSecond().equals(cmp.get(i).getSecond()) &&
                            list.get(i).getSecond() < 3) {
                        continue loop1;
                    }
                }
                ++ ret;
            }
        }
        return ret;
    }

    private List<Pair<Character, Integer>> formList(String str) {
        int lens = str.length();
        if (lens == 0) {
            return new ArrayList<>();
        }
        List<Pair<Character, Integer>> list = new ArrayList<>();
        int index1 = 0, index2 = 0;
        while (index2 < lens) {
            while (index2 < lens && str.charAt(index1) == str.charAt(index2)) {
                ++ index2;
            }
            list.add(new Pair<>(str.charAt(index1), index2 - index1));
            index1 = index2;
        }
        return list;
    }
}
