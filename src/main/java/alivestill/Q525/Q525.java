package alivestill.Q525;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Q525 {

    public static final Solution so = new Solution();

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/Q525/input.json");
        String content = FileUtils.readFileToString(file, "UTF-8");
        Gson gson = new Gson();
        Model model = gson.fromJson(content, Model.class);
        long begin = System.currentTimeMillis();
        so.findMaxLength(model.list.stream().mapToInt(Integer::valueOf).toArray());
        long end = System.currentTimeMillis();
        System.out.printf("%dms", end - begin); // 995ms, TLE
    }
}

class Model {
    List<Integer> list;
}

class Solution {
    /**
     * prefix sum array, namely two pass, actually one
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        int lens = nums.length;
        int[] sumprefix = new int[lens + 1];    // sum prefix of 0s, sumprefix[i] = number of 0s of nums[0:i],
        for (int i = 1; i <= lens; ++ i) {
            sumprefix[i] = sumprefix[i - 1] + (nums[i - 1] == 0 ? 1 : 0);
        }
        int max = 0;
        for (int i = 0; i <= lens; ++ i) {
            for (int j = i + 2; j <= lens; j += 2) {
                if ((sumprefix[j] - sumprefix[i]) * 2 == j - i) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }

}

// FIXED, wrong algorithm
//    /**
//     *
//     * @param nums
//     * @param leftParen as mapping of '('
//     * @param rightParen as mapping of ')'
//     * @return
//     */
//    private int longestValidParentheses(int[] nums, int leftParen, int rightParen) {
//        int lens = nums.length;
//        int pos = 0;
//        while (nums[pos] == rightParen) {
//            ++ pos;
//        }
//        int max = 0;
//        int subarrayLength = 0;
//        int leftCnt = 0;
//        while (pos < lens) {
//            if (nums[pos] == leftParen) {
//                ++ leftCnt;
//            } else {
//                if (leftCnt > 0) {
//                    subarrayLength += 2;
//                    -- leftCnt;
//                }
//            }
//            max = Math.max(max, subarrayLength);
//            if (leftCnt == 0) {
//                subarrayLength = 0;
//            }
//            ++ pos;
//        }
//    }
