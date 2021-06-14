package alivestill.Q733;

import java.util.HashSet;
import java.util.Set;

public class Q733 {
}

class Solution {
    int height, width;
    RowColumnSet set = new RowColumnSet();

    /**
     * naive
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        height = image.length;
        if (height == 0) {
            return image;
        }
        width = image[0].length;
        if (width == 0) {
            return image;
        }
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    public void fill(int[][] image, int row, int column,
                     int originalColor, int newColor) {
        if (row < 0 || row >= height
                || column < 0 || column >= width
                || set.exist(row, column)) {
            return ;
        }
        if (image[row][column] == originalColor) {
            image[row][column] = newColor;
            set.add(row, column);
            fill(image, row - 1, column, originalColor, newColor);
            fill(image, row, column - 1, originalColor, newColor);
            fill(image, row + 1, column, originalColor, newColor);
            fill(image, row, column + 1, originalColor, newColor);
        }
    }

    class RowColumnSet {
        Set<Integer> set = new HashSet<>();

        public void add(int row, int column) {
            set.add((row << 16) + column);
        }

        public boolean exist(int row, int column) {
            Integer construct = (row << 16) + column;
            return set.contains(construct);
        }
    }
}
