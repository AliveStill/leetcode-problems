package alivestill.Q820;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class Q820 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        int ret = so.minimumLengthEncoding(new String[]
                {"time","me","bell"});
        System.out.println(ret);
    }
}

class Solution {
    /**
     * naive
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        int lens = words.length;
        if (lens == 0) {
            return 0;
        }
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        TreeSet<String> set = new TreeSet<>(
                (a, b) -> {
                    if (a.equals(b)) {
                        return 0;
                    } else if (a.length() == b.length()) {
                        return b.compareTo(a);
                    }
                    return b.length() - a.length();
                }); // reverse natural order
        set.add(words[0]);
        int ret = words[0].length();
        for (int i = 1; i < lens; ++ i) {
            boolean endsMatch = false;
            // ascending iterator
            for (String s : set) {
                if (s.endsWith(words[i])) {
                    endsMatch = true;
                    break;
                }
            }
            if (!endsMatch) {
                ret += words[i].length();
                if (set.contains(words[i])) {
                    System.out.println("holy shit");
                }
                set.add(words[i]);
            }
        }
        return ret + set.size();
    }
}
