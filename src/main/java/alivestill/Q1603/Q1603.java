package alivestill.Q1603;

public class Q1603 {
}

// naive
class ParkingSystem {

    int[] available = new int[3];

    public ParkingSystem(int big, int medium, int small) {
        available[0] = big;
        available[1] = medium;
        available[2] = small;
    }

    public boolean addCar(int carType) {
        if (available[carType - 1] <= 0) {
            return false;
        } else {
            --available[carType - 1];
            return true;
        }
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */
