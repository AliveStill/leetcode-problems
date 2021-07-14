package alivestill.whatever;

import java.util.ArrayList;
import java.util.List;

public class FullPermutation {
    List<String> list = new ArrayList<>();

    public List<String> fullPermutate(String str) {
        permutate(new StringBuilder(str), 0);
        return list;
    }

    public void permutate(StringBuilder str, int pos) {
        if (pos == str.length()) {
            list.add(str.toString());
            return ;
        } else {
            for (int i = pos; i < str.length(); ++ i) {
                if (!hasDuplicate(str, pos, i)) {
                    char ch = str.charAt(i);
                    str.setCharAt(i, str.charAt(pos));
                    str.setCharAt(pos, ch);
                    permutate(str, pos + 1);
                    str.setCharAt(pos, str.charAt(i));
                    str.setCharAt(i, ch);
                }
            }
        }
    }
    // ensure no same character as str[endIndex] in range [beginIndex, endIndex)
    private boolean hasDuplicate(StringBuilder str, int beginIndex, int endIndex) {
        for (int i = beginIndex; i < endIndex; ++ i) {
            if (str.charAt(i) == str.charAt(endIndex)) {
                return true;
            }
        }
        return false;
    }
}

class FullPermutationTest {

    public static void main(String[] args) {
        FullPermutation fp = new FullPermutation();
        for (String str : fp.fullPermutate("112")) {
            System.out.println(str);
        }
    }
}