import java.util.PriorityQueue;
import java.util.ArrayList;

public class Lab2b {
    public static double[] simplifyShape(double[] poly, int k) {
        PriorityQueue<DLList.Node<Point>> queue = new PriorityQueue<>();
        DLList<Point> points = new DLList<>();

        Point previous = new Point(poly[0], poly[1]);
        DLList.Node<Point> currentNode = points.addFirst(previous);

        Point current = new Point(poly[2], poly[3]);
        currentNode = points.insertAfter(current, currentNode);

        for (int i = 4; i < poly.length; i += 2) {
            // initate the next point in the list with 0 importance
            Point next = new Point(poly[i], poly[i + 1]);

            // calculate the importance of the current point using the previous and the next
            current.setImportance(previous, next);

            // add the current point to the priority queue and the linked list
            queue.add(currentNode);
            currentNode = points.insertAfter(next, currentNode);

            // update references for the next iteration
            previous = current;
            current = next;
        }

        while (queue.size() + 2 > k) {
            DLList.Node<Point> removed = queue.remove();
            DLList.Node<Point> prev  = removed.prev;
            DLList.Node<Point> next = removed.next;

            points.remove(removed);

            // if prev is the first point, do nothing
            if (prev.prev != null) {
                prev.getValue().setImportance(prev.prev.getValue(), next.getValue());
                queue.remove(prev);
                queue.add(prev);
            }

            // if next is the last point, do nothing
            if (next.next != null) {
                next.getValue().setImportance(prev.getValue(), next.next.getValue());
                queue.remove(next);
                queue.add(next);
            }
        }

        double[] newPoly = new double[k * 2];
        int i = 0;
        while (points.getFirst() != null) {
            DLList.Node<Point> p = points.getFirst();
            Point point = p.getValue();

            newPoly[i] = point.getX();
            i++;
            newPoly[i] = point.getY();
            i++;

            points.remove(p);
        }

        return newPoly;
    }
}

class Point implements Comparable<Point> {
    private double x;
    private double y;
    private double importance;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.importance = 0;
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

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.importance + ")";
    }
}
