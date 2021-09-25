package alivestill.Q833;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q833 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        //  s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
        // Output: "eeebffff"
        String ret = so.findReplaceString(
                "abcd",
                new int[]{0, 2},
                new String[]{"a", "cd"},
                new String[]{"eee", "ffff"});
        System.out.println(ret);
    }

    @Test
    public void test1() {
        // "vmokgggqzp"
        //[3,5,1]
        //["kg","ggq","mo"]
        //["s","so","bfr"]
        // return: "vbfrssozp"
        String ret = so.findReplaceString(
                "vmokggggzp",
                new int[]{3, 5, 1},
                new String[]{"kg", "ggq", "mo"},
                new String[]{"s", "so", "bfr"});
        System.out.println(ret);
    }
}

class Solution {
    /**
     * naive
     * @param s
     * @param indices
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int lens = s.length();
        int k = indices.length;
        List<Container> list = new ArrayList<>();
        for (int i = 0; i < k; ++ i) {
            list.add(new Container(indices[i], sources[i], targets[i]));
        }
        list.sort((a, b) -> a.index - b.index);
        StringBuilder sb = new StringBuilder();
        if (list.get(0).index != 0 && lens > 1) {
            sb.append(s.substring(0, list.get(0).index));
        }

        int lastPos = -1;   // last turn
        for (int i = 0; i < k; ++ i) {
            int strLength = list.get(i).source.length();
            int beginIndex = list.get(i).index;
            String targetString = list.get(i).target;
            if (beginIndex + strLength <= lens &&
                    s.substring(beginIndex, beginIndex + strLength).equals(list.get(i).source)) {
                sb.append(targetString);
                if (i != k - 1) {
                    sb.append(s.substring(beginIndex + strLength, list.get(i + 1).index));
                } else {
                    lastPos = beginIndex + strLength;
                }
            } else {
                if (i != k - 1) {
                    sb.append(s.substring(beginIndex, list.get(i + 1).index));
                } else {
                    lastPos = beginIndex;
                }
            }
        }
        sb.append(s.substring(lastPos, lens));
        return sb.toString();
    }

    class Container {
        int index;
        String source;
        String target;

        public Container(int index, String source, String target) {
            this.index = index;
            this.source = source;
            this.target = target;
        }

    }
}
