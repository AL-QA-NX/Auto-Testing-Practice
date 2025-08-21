package lebedev.auto.testing.task3;

public class PointTask3 {

    double p1x;
    double p1y;
    double p2x;
    double p2y;

    public PointTask3 (double p1x, double p1y, double p2x, double p2y) {
        this.p1x = p1x;
        this.p1y = p1y;
        this.p2x = p2x;
        this.p2y = p2y;
    }

    public double distance () {
        double x_difference = Math.pow(p2x-p1x, 2);
        double y_difference = Math.pow(p2y-p1y, 2);
        return Math.sqrt(x_difference + y_difference);
    }
}
