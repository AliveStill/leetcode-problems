package alivestill.Q1357;

import java.util.HashMap;
import java.util.Map;

public class Q1357 {

    public static void main(String[] args) {
        // ["Cashier","getBill","getBill","getBill","getBill","getBill","getBill","getBill"]
        // [[3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]],[[1,2],[1,2]],[[3,7],[10,10]],[[1,2,3,4,5,6,7],[1,1,1,1,1,1,1]],[[4],[10]],[[7,3],[10,10]],[[7,5,3,1,6,4,2],[10,10,10,9,9,9,7]],[[2,3,5],[5,3,2]]]
        // expected:
        // [null,500.00000,4000.00000,800.00000,4000.00000,4000.00000,7350.00000,2500.00000]
        Cashier cashier = new Cashier(3, 50, new int[]{1, 2, 3, 4, 5, 6, 7},
                new int[]{100, 200, 300, 400, 300, 200, 100});
        System.out.println(cashier.getBill(new int[]{1, 2}, new int[]{1, 2}));
        System.out.println(cashier.getBill(new int[]{3, 7}, new int[]{10, 10}));

    }
}

// naive
class Cashier {

    int discount;
//    int[] products;
    int[] prices;
    int occur = 0;
    Map<Integer, Integer> map = new HashMap<>();
    int threshold;

    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.discount = discount;
        threshold = n;
//        this.products = new int[products.length];
//        System.arraycopy(products, 0, this.products, 0, products.length);
        this.prices = new int[prices.length];
        System.arraycopy(prices, 0, this.prices, 0, prices.length);
        for (int i = 0; i < products.length; ++ i) {
            map.put(products[i], i);    // id to index map
        }
    }

    public double getBill(int[] product, int[] amount) {
        double sum = 0d;
        for (int i = 0; i < product.length; ++ i) {
            int index = map.get(product[i]);
            sum += prices[index] * amount[i];
        }
        ++ occur;
        if (occur == threshold) {
            sum *= (100 - discount);
            occur = 0;
        }
        return sum;
    }
}

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj.getBill(product,amount);
 */
