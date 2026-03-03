

class Parking {

    private int big;
    private int medium;
    private int small;

    public Parking(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }
    
    public boolean addCar(int carType) {
        if (carType == 1 && big > 0) {
            big--;
            return true;
        } 
        else if (carType == 2 && medium > 0) {
            medium--;
            return true;
        } 
        else if (carType == 3 && small > 0) {
            small--;
            return true;
        }
        return false;
    }
}

public class q1 {
    public static void main(String[] args) {

        Parking obj = new Parking(1, 1, 0);

        System.out.println(obj.addCar(1)); // true
        System.out.println(obj.addCar(2)); // true
        System.out.println(obj.addCar(3)); // false
        System.out.println(obj.addCar(1)); // false
    }
}