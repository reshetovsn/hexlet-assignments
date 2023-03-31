package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        double commonArea = this.area + this.balconyArea;
        return commonArea;
    }

    @Override
    public int compareTo(Home home) {
        if (home.getArea() > this.getArea()) {
            return -1;
        } else if (this.getArea() > home.getArea()) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + this.floor + " этаже";
    }
}
// END
