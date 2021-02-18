package alivestill.Q200;

public class Q200 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
//        System.out.println(so.numIslands(matrix));
        char [][] table = new char[][] {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'}
        };
        System.out.println(so.numIslands(table));
    }
}

class Solution {
    /// @brief union-find
    public int numIslands(char[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int count = height * width;
        int[][] directionArray = new int[][] {
                {0, -1}, {-1, 0}, {0, 1}, {1, 0}
        };
        UnionFind uf = new UnionFind((height << 9) + width);
        for (int i = 0; i < height; ++ i) {
            for (int j = 0; j < width; ++ j) {
                if ('0' == grid[i][j]) {
                    -- count;
                } else {
                    for (int[] inc : directionArray) {
                        int x = i + inc[0];
                        int y = j + inc[1];
                        if (x >= 0 && x < height && y >= 0 && y < width &&
                                grid[x][y] == '1' && uf.find((i << 9) + j) != uf.find((x << 9) + y)) {
                            uf.union((i << 9) + j, (x << 9) + y);
                            -- count;
                        }
                    }
                }
            }
        }
        return count;
    }

    class UnionFind {
        private int count;
        private int[] array;
        public UnionFind(int count) {
            this.count = count;
            array = new int[count];
            for (int i = 0; i < count; ++ i) {
                array[i] = i;
            }
        }
        public void union(int xidx, int yidx) {
            // trivial, make the eldest ancestor of x father of eldest ancestor of y
            array[find(yidx)] = array[find(xidx)];
        }
        /// @brief with compact
        public int find(int index) {
            if (index == array[index]) {
                return index;
            }
            int father = find(array[index]);
            array[index] = father;
            return father;
        }
    }
}
