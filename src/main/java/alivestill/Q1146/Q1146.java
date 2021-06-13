package alivestill.Q1146;


import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Q1146 {

    @Test
    public void test1() {
        SnapshotArray sa = new SnapshotArray(1);
        // ["SnapshotArray","set","set","set","snap","get","snap"]
        //[[1],[0,4],[0,16],[0,13],[],[0,0],[]]
        sa.set(0, 4);
        sa.set(0, 16);
        sa.set(0, 13);
        sa.snap();
        assertEquals(sa.get(0, 0), 13);
        sa.snap();
    }
}

class SnapshotArray {

    // path compress
    class Data {
        int val;
        RoundWrapper snapRound;
        Data next;
        public Data(int val, RoundWrapper round) {   // share same round
            this.val = val;
            this.snapRound = round;
            next = null;
        }
    }

    class RoundWrapper {
        int round;

        public RoundWrapper(int round) {
            this.round = round;
        }
    }

    int lens;
    Data[] array = new Data[50000];
    Data[] tailArray = new Data[50000];
    RoundWrapper snapRound;

    public SnapshotArray(int length) {
        snapRound = new RoundWrapper(-1);
        this.lens = length;
        for (int index = 0; index < lens; ++ index) {
            tailArray[index] = array[index] = new Data(0, snapRound);
        }
    }

    public void set(int index, int val) {
        tailArray[index].next = new Data(val, snapRound);
        tailArray[index].snapRound = new RoundWrapper(snapRound.round);
        tailArray[index] = tailArray[index].next;
    }

    public int snap() {
        return ++snapRound.round;
    }

    public int get(int index, int snap_id) {
        if (index >= lens) {
            return -1;  // never occur
        }
        Data head = array[index];
        // todo, more space-efficient path compression can be done here
        while (head != null && snap_id > head.snapRound.round) {
            head = head.next;
        }
        return head.val;
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
