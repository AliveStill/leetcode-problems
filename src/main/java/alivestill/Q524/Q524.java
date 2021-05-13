package alivestill.Q524;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q524 {

    public final static Solution so = new Solution();

    public static void main(String[] args) {
        /// Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
        String s = "abpcplea";
        List<String> list = new ArrayList<>() {{
            this.add("ale");
            this.add("apple");
            this.add("monkey");
            this.add("plea");
        }};
        String ret = so.findLongestWord(s, list);
        assert false;
        assert ret.equals("apple"); // pass
    }

    @Test /* 1588ms, timed out */
    public void test1() throws IOException {
        File file = new File("src/main/resources/Q524/input.json");
        String content = FileUtils.readFileToString(file, "UTF-8");
        Gson gson = new Gson();
        Model model = gson.fromJson(content, Model.class);
        long begin = System.currentTimeMillis();
        String ret = so.findLongestWord(model.s, model.dict);
        long end = System.currentTimeMillis();
        System.out.printf("%dms", end - begin);
    }
}

class Model {
    // name of attributes must be consistent with the name of attributes of Json file
    String s;
    List<String> dict;
}

class SolutionV2 {
    /**
     * naive, TLE
     * @param s
     * @param dictionary
     * @return
     */
    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary, (s1, s2) -> {
            if (s1.length() > s2.length()) {
                return -1;
            } else if (s2.length() > s1.length()) {
                return 1;
            } else {
                return s1.compareTo(s2);
            }
        });
        for (String ele : dictionary) {
            if (longestCommonSubsequence(ele, s) == ele.length()) {
                return ele;
            }
        }
        return "";
    }


    /**
     * naive dp, copied from {@link alivestill.Q1143.Solution#longestCommonSubsequence(String, String)}
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int lens1 = text1.length();
        int lens2 = text2.length();
        int[][] matrix = new int[lens1 + 1][lens2 + 1];
        for (int i = 1; i <= lens1; ++ i) {
            for (int j = 1; j <= lens2; ++ j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;    // or pick max?
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j - 1],
                            Math.max(matrix[i - 1][j], matrix[i][j - 1]));
                }
            }
        }
        return matrix[lens1][lens2];
    }
}

class Solution {
    /**
     * naive
     * @param s
     * @param dictionary
     * @return
     */
    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary, (s1, s2) -> {
            if (s1.length() > s2.length()) {
                return -1;
            } else if (s2.length() > s1.length()) {
                return 1;
            } else {
                return s1.compareTo(s2);
            }
        });
        for (String ele: dictionary) {
            if (containsSequence(s, ele)) {
                return ele;
            }
        }
        return "";
    }

    private boolean containsSequence(String text, String pattern) {
        int tlens = text.length();
        int plens = pattern.length();
        if (tlens < plens) {
            return false;
        }
        int tpos = 0, ppos = 0;
        while (tpos != tlens && ppos != plens) {
            if (text.charAt(tpos) == pattern.charAt(ppos)) {
                ++ ppos;
            }
            ++ tpos;
        }
        return ppos == plens;
    }
}

