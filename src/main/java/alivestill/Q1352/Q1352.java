package alivestill.Q1352;

import java.util.Arrays;
import java.util.List;

public class Q1352 {
}

// tabulation
class ProductOfNumbersV0 {

    int[] array = new int[40000];
    int size = 0;

    public ProductOfNumbersV0() {
        Arrays.fill(array, 1);
    }

    public void add(int num) {
        ++ size;
        for (int i = size; i >= 0; -- i) {
            array[i] *= num;
        }
    }

    public int getProduct(int k) {
        return array[size - k];
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */

// tabulation
class ProductOfNumbers {

    int[] array = new int[40001];
    int size = 0;
    int zeroIndex = 0;

    public ProductOfNumbers() {
        array[0] = 1;  // array[0] is preserved
    }

    public void add(int num) {
        ++ size;
        if (num == 0) {
            array[size] = 1;
            zeroIndex = size;
        } else {
            array[size] = array[size - 1] * num;
        }
    }

    public int getProduct(int k) {
        if (zeroIndex + k > size) {
            return 0;
        } else {
            return array[size] / array[size - k];
        }
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
