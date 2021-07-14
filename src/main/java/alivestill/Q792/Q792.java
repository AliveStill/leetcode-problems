package alivestill.Q792;

import java.util.HashSet;
import java.util.Set;

public class Q792 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        // "qlhxagxdqh"
        //["qlhxagxdq","qlhxagxdq","lhyiftwtut","yfzwraahab"]
        // 2
        System.out.println(so.numMatchingSubseq("qlhxagxdqh",
                new String[]{"qlhxagxdq","qlhxagxdq","lhyiftwtut","yfzwraahab"}));
    }
}

class Solution {
    /**
     * brute-force with memo
     * @param s
     * @param words
     * @return
     */
    public int numMatchingSubseq(String s, String[] words) {
        int lens = s.length();
        if (lens == 0) {
            return 0;
        }
        int ret = 0;
        Set<String> positiveSet = new HashSet<>();  // accelerate
        Set<String> negativeSet = new HashSet<>();
        for (String word : words) {
            if (positiveSet.contains(word)) {
                ++ ret;
                continue;
            } else if (negativeSet.contains(word)) {
                continue;
            }
            int wordLength = words.length;
            if (wordLength <= lens && isSubsequence(s, word)) {
                ++ ret;
                positiveSet.add(word);
            } else {
                negativeSet.add(word);
            }
        }
        return ret;
    }


    private boolean isSubsequence(String word, String sub) {
        int lens1 = word.length();
        int lens2 = sub.length();
        int pos1 = 0, pos2 = 0;
        while (pos1 < lens1 && pos2 < lens2) {
            if (word.charAt(pos1) == sub.charAt(pos2)) {
                ++ pos2;
            }
            ++ pos1;
        }
        return pos2 == lens2;
    }
}