package alivestill.utils;

/// @brief implementation of Union-Find algorithm(or data structure)
public class UnionFind {
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
    /// @brief find with path compact
    public int find(int index) {
        if (index == array[index]) {
            return index;
        }
        int father = find(array[index]);
        array[index] = father;
        return father;
    }
}
