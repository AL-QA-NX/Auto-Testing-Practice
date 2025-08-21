package lebedev.auto.testing.task3;

import lebedev.auto.testing.task2.Point;

public class LaunchingClassTask3 {
    public static void main(String[] args){
        Point p = new Point (10, 8, 8, 6);
        System.out.printf("Разница между введёными точками = %.2f", p.distance());
    }
}
