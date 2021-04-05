package alivestill.Q1556;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Q1556 {
}

// naive
class OrderedStream {

    TreeSet<Integer> available = new TreeSet<>();

    String[] data;
    int chunkBeginIndex = 1;    // index of next to be transported chunk

    public OrderedStream(int n) {
        data = new String[n + 1];  // store string, data[0] is useless
        for (int i = 1; i <= n + 1; ++ i) { // insert n+1 as sentinel
            available.add(i);
        }
    }

    public List<String> insert(int idKey, String value) {
        data[idKey] = value;
        available.remove(idKey);
        List<String> list = new ArrayList<>();
        if (idKey < available.first()) {
            int end = available.first();
            for (int i = chunkBeginIndex; i < end; ++ i) {
                list.add(data[i]);
            }
            chunkBeginIndex = end;
        }
        return list;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream obj = new OrderedStream(n);
 * List<String> param_1 = obj.insert(idKey,value);
 */