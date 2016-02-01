import java.util.PriorityQueue;

public class Lab2b {
    public static double[] simplifyShape(double[] poly, int k) {
        return new double[k];
    }
}

class Point implements Comparable<Point> {
    private double x;
    private double y;
    private double importance;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getImportance() {
        return this.importance;
    }

    public void setImportance(Point l, Point r) {
        if (l == null || r == null) {
            throw new IllegalArgumentException("setImportance: null values not permitted");
        }

        double l1 = calcDist(l, this);
        double l2 = calcDist(this, r);
        double l3 = calcDist(l, r);
        this.importance = l1 + l2 - l3;
    }

    private static double calcDist(Point p1, Point p2) {
        double distX = Math.abs(p1.getX() - p2.getX());
        double distY = Math.abs(p1.getY() - p2.getY());
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }

    @Override
    public int compareTo(Point p) {
        return Double.compare(this.importance, p.getImportance());
    }
}
