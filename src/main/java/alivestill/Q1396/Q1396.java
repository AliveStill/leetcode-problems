package alivestill.Q1396;

import java.util.*;

public class Q1396 {
}

class UndergroundSystem {

    class Pair<T1, T2> {
        T1 first;
        T2 second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }

        public T1 getFirst() {
            return first;
        }

        public void setFirst(T1 first) {
            this.first = first;
        }

        public T2 getSecond() {
            return second;
        }

        public void setSecond(T2 second) {
            this.second = second;
        }
    }

    // result map, key pair as checkIn, checkOut station
    // value pair as sum, count
    Map<Pair<String, String>, Pair<Integer, Integer>> map = new HashMap<>();

    // Pair.first is name of check in station, Pair.second is time spent to travel
    Map<Integer, Pair<String, Integer>> checkInMap = new HashMap<>();

    public UndergroundSystem() {
        // left empty
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        // no need to check duplicate or validate the track if the
        // problem description is consistent, so remove will never
        // return null
        Pair<String, Integer> pair = checkInMap.remove(id);
        Pair<String, String> key = new Pair<>(pair.getFirst(), stationName);
        if (map.containsKey(key)) {
            Pair<Integer, Integer> res = map.get(key);
            res.setFirst(res.getFirst() + (t - pair.getSecond()));
            res.setSecond(res.getSecond() + 1);
        } else {
            Pair<Integer, Integer> res = new Pair<>(t - pair.getSecond(), 1);
            map.put(key, res);
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        Pair<Integer, Integer> pair = map.get(new Pair<>(startStation, endStation));
        return pair.getFirst() / (double)pair.getSecond();
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
