package alivestill.Q523;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Q523 {

    public static Solution so = new Solution();

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        File file = new File("src/main/resources/Q523/input.json");
        String content = FileUtils.readFileToString(file, "UTF-8");
        Model model = gson.fromJson(content, Model.class);
        long begin = System.currentTimeMillis();
        int[] array = (int[])model.nums.stream().mapToInt(Integer::valueOf).toArray();
        so.checkSubarraySum(array, model.k);
        long end = System.currentTimeMillis();
        System.out.printf("%dms", end - begin); // 45ms
        //        JsonElement jsonElement = jsonParser.parse(content);
//        jsonElement.getAsJsonArray();

    }

    @Test
    public void test1() {
        int[] array = new int[] {5, 0, 0, 0};
        boolean b = so.checkSubarraySum(array, 3);
        assert b;
    }

    @Test
    public void test2() {
        int[] array = new int[]{1, 2, 3};
        boolean b = so.checkSubarraySum(array, 6);
        assert b;
    }

    @Test
    public void test3() {
        int[] array = new int[]{1, 2, 3};
        boolean b = so.checkSubarraySum(array, 5);
        assert b;
    }
}

class Model {
    List<Integer> nums;
    Integer k;
}

class SolutionV2 {
    /// @brief scan
    public boolean checkSubarraySum(int[] nums, int k) {
        int lens = nums.length;
        if (lens < 2) {
            return false;
        }
        Set<Integer> set = new HashSet<>(); // module k set
        set.add(nums[0]);
        for (int i = 1; i < lens; ++ i) {
            Set<Integer> tmp = new HashSet<>();
            Iterator<Integer> iter = set.iterator();
            while (iter.hasNext()) {
                int rem = (iter.next() + nums[i]) % k;
                if (rem == 0) {
                    return true;
                } else {
                    tmp.add(rem);
                }
            }
            tmp.add(nums[i] % k);
            set = tmp;      // discard old set
        }
        return false;
    }
}

class Solution {
    /// @brief scan
    /// @note Very Large k specified version, last version TLE
    /// @details record increment if necessary to avoid heavy update of the set,
    ///     compared with the last version
    public boolean checkSubarraySum(int[] nums, int k) {
        int lens = nums.length;
        if (lens < 2) {
            return false;
        }
        int pos = 0;
        while (pos < lens - 1) {
            if (nums[pos] == 0 && nums[pos + 1] == 0) { // continuous 0s
                return true;
            } else {
                ++ pos;
            }
        }
        TreeSet<Integer> set = new TreeSet<>(); // module k set
        set.add(nums[0] % k);
        int factor = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i < lens; ++ i) {
            if (set.last() + nums[i] + factor < k) {
                    factor += nums[i];
                    list.addLast(nums[i]);
            } else {
                int sum = 0;
                while (!list.isEmpty()) {   // the missing postfix
                    sum += list.pollLast();
                    if ((sum + nums[i]) % k == 0) {
                        return true;
                    }
                }
                TreeSet<Integer> tmp = new TreeSet<>();
                Iterator<Integer> iter = set.iterator();
                while (iter.hasNext()) {
                    int rem = (iter.next() + nums[i] + factor) % k;
                    if (rem == 0) {
                        return true;
                    } else {
                        tmp.add(rem);
                    }
                }
                tmp.add(nums[i] % k);
                set = tmp;      // discard old set
                factor = 0;
            }
        }
        return false;
    }
}
