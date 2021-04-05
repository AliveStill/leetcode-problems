package alivestill.Q380;

import java.util.*;

public class Q380 {
}

// insert: O(1)
// remove: O(1)
// getRandom: O(n)
class RandomizedSetV2 {

    Set<Integer> container = new HashSet<>();
    Random random = new Random();

    /** Initialize your data structure here. */
    public RandomizedSetV2() {
        // left empty
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean exist = container.contains(val);
        if (exist) {
            return false;
        } else {
            container.add(val);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean exist = container.contains(val);
        if (exist) {
            container.remove(val);
            return true;
        } else {
            return false;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Integer[] array = container.toArray(new Integer[0]);
        return array[random.nextInt() % array.length];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

class RandomizedSet {

    Map<Integer, Integer> indexMapper = new HashMap<>();
    Random random = new Random();
    int[] array = new int[100000];  // at most 10 ^ 5 elements
    int arraySize = 0;


    /** Initialize your data structure here. */
    public RandomizedSet() {
        // left empty
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        // exists
        if (indexMapper.get(val) != null) {
            return false;
        } else {
            array[arraySize] = val;
            indexMapper.put(val, arraySize);
            ++ arraySize;
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        // doesn't exist
        if (indexMapper.get(val) == null) {
            return false;
        } else {
            int index = indexMapper.remove(val);
            array[index] = array[arraySize - 1];
            -- arraySize;
            indexMapper.put(array[index], index);
            return true;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return array[random.nextInt(arraySize)];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
