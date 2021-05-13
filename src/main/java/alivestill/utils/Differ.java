package alivestill.utils;


import alivestill.utils.TextColor.RedColor;

public class Differ {

    public static RedColor redColor = new RedColor();
    public static void main(String[] args) {
        Double[] output = new Double[] {null,null,155.00000,null,110.50000,null,114.00000,null,90.00000,null,66.00000,null,69.50000,null,73.00000,null,69.50000,null,73.00000,null,69.50000,null,73.00000,null,74.00000,null,75.00000,null,69.50000,null,66.00000,null,63.00000,null,66.00000,null,69.50000,null,66.00000,null,69.50000,null,66.00000,null,69.50000,null,66.00000,null,69.50000,null,73.00000,null,69.50000,null,73.00000,null,74.00000,null,75.00000,null,77.00000,null,79.00000,null,77.00000,null,79.00000,null,77.00000,null,79.00000,null,80.50000,null,79.00000,null,80.50000,null,79.00000,null,79.00000,null,79.00000,null,80.50000,null,82.00000,null,80.50000,null,79.00000,null,79.00000,null,79.00000,null,79.00000,null,79.00000,null,77.00000,null,75.00000,null,74.00000,null,73.00000,null,74.00000,null,75.00000,null,74.00000,null,75.00000,null,74.00000,null,75.00000,null,77.00000,null,79.00000,null,77.00000,null,79.00000,null,77.00000,null,75.00000,null,77.00000,null,79.00000,null,77.00000,null,79.00000,null,77.00000,null,75.00000,null,74.00000,null,75.00000,null,76.00000,null,77.00000,null,76.00000,null,75.00000,null,74.00000,null,73.00000,null,72.00000,null,71.00000,null,72.00000,null,73.00000,null,72.00000,null,71.00000,null,71.00000,null,71.00000,null,72.00000,null,71.00000,null,72.00000,null,71.00000,null,71.00000,null,71.00000,null,70.00000,null,69.00000,null,68.00000,null,69.00000,null,70.00000,null,69.00000,null,68.00000,null,67.00000,null,66.50000,null,66.00000,null,66.50000,null,67.00000,null,66.50000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,65.50000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,66.50000,null,67.00000,null,66.50000,null,66.00000,null,66.50000,null,67.00000,null,66.50000,null,67.00000,null,68.00000,null,67.00000,null,68.00000,null,67.00000,null,66.50000,null,66.00000,null,66.50000,null,67.00000,null,68.00000,null,69.00000,null,69.50000,null,69.00000,null,68.00000,null,67.00000,null,66.50000,null,66.00000,null,66.00000,null,66.00000,null,65.50000,null,65.00000,null,65.50000,null,66.00000,null,66.00000,null,66.00000,null,66.50000,null,67.00000,null,67.00000,null,67.00000,null,66.50000,null,67.00000,null,67.00000,null,67.00000,null,68.00000,null,67.00000};
        Double[] expect = new Double[] {null,null,155.00000,null,110.50000,null,114.00000,null,90.00000,null,66.00000,null,69.50000,null,73.00000,null,69.50000,null,73.00000,null,69.50000,null,73.00000,null,74.00000,null,73.00000,null,69.50000,null,66.00000,null,63.00000,null,66.00000,null,69.50000,null,66.00000,null,69.50000,null,66.00000,null,69.50000,null,66.00000,null,69.50000,null,73.00000,null,69.50000,null,73.00000,null,74.00000,null,75.00000,null,77.00000,null,79.00000,null,77.00000,null,79.00000,null,77.00000,null,79.00000,null,80.50000,null,79.00000,null,80.50000,null,79.00000,null,79.00000,null,79.00000,null,80.50000,null,82.00000,null,80.50000,null,79.00000,null,79.00000,null,79.00000,null,79.00000,null,79.00000,null,77.00000,null,75.00000,null,74.00000,null,73.00000,null,74.00000,null,75.00000,null,74.00000,null,75.00000,null,74.00000,null,75.00000,null,77.00000,null,75.00000,null,77.00000,null,79.00000,null,77.00000,null,75.00000,null,77.00000,null,79.00000,null,77.00000,null,79.00000,null,77.00000,null,75.00000,null,74.00000,null,75.00000,null,76.00000,null,77.00000,null,76.00000,null,75.00000,null,74.00000,null,73.00000,null,72.00000,null,71.00000,null,72.00000,null,73.00000,null,72.00000,null,71.00000,null,71.00000,null,71.00000,null,72.00000,null,71.00000,null,72.00000,null,71.00000,null,71.00000,null,71.00000,null,70.00000,null,69.00000,null,68.00000,null,69.00000,null,70.00000,null,69.00000,null,68.00000,null,67.00000,null,66.50000,null,66.00000,null,66.50000,null,67.00000,null,66.50000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,65.50000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,66.00000,null,66.50000,null,67.00000,null,66.50000,null,66.00000,null,66.50000,null,67.00000,null,66.50000,null,67.00000,null,68.00000,null,67.00000,null,68.00000,null,67.00000,null,66.50000,null,66.00000,null,66.50000,null,67.00000,null,68.00000,null,69.00000,null,69.50000,null,69.00000,null,68.00000,null,67.00000,null,66.50000,null,66.00000,null,66.00000,null,66.00000,null,65.50000,null,65.00000,null,65.50000,null,66.00000,null,66.00000,null,66.00000,null,66.50000,null,67.00000,null,67.00000,null,67.00000,null,66.50000,null,67.00000,null,67.00000,null,67.00000,null,68.00000,null,67.00000};
        if (output.length != expect.length) {
            System.err.println("Different length!");
        } else {
            System.out.println("Same Length!");
        }
        System.out.println("output  expect  equal");
        for (int i = 0; i < output.length; ++ i) {
            System.out.print(output[i] + "\t" + expect[i] + "\t");
            if (doubleApproximate(output[i], expect[i])) {
                System.out.println(true);
            } else {
                System.out.println(redColor.PREFIX + false + redColor.POSTFIX);
            }
        }
    }

    public static boolean doubleApproximate(Double d1, Double d2) {
        if (d1 == null && d2 == null) {
            return true;
        } else if (d1 == null || d2 == null) {
            return false;
        }
        return Math.abs(d1 - d2) <= 1e-5;
    }
}
