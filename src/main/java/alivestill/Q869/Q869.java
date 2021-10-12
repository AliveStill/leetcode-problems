package alivestill.Q869;

import alivestill.whatever.FullPermutation;

import java.util.List;

public class Q869 {
}

class Solution {
    /**
     * full permutation & power of 2
     * @param n
     * @return
     */
    public boolean reorderedPowerOf2(int n) {
        // may test in the procedure instead to accelerate searching
        List<String> list = fp.fullPermutate(String.valueOf(n));
        for (String ele : list) {
            // won't exceed Integer.MAX_VALUE
            int num = Integer.parseInt(ele);
            if ((num & (num - 1)) == 0) {
                return true;
            }
        }
        return false;
    }

    FullPermutation fp = new FullPermutation();
}
