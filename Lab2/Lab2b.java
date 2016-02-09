import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;

public class Lab2b {
    public static double[] simplifyShape(double[] poly, int k) {
        PriorityQueue<DLList<Point>.Node> queue = new PriorityQueue<>(poly.length - 2, new PointComparator());
        DLList<Point> points = new DLList<>();

        // Start by initiating the first two points in the list.
        DLList<Point>.Node currentNode = points.addFirst(new Point(poly[0], poly[1]));
        currentNode = points.insertAfter(new Point(poly[2], poly[3]), currentNode);

        // Iterate through the remaining points. In each iteration we initiate
        // the "next" point and calculate the importance for the current one
        // using the previous and next points.
        for (int i = 4; i < poly.length; i += 2) {
            Point point = new Point(poly[i], poly[i + 1]);
            DLList<Point>.Node nextNode = points.insertAfter(point, currentNode);
            queue.add(currentNode);
            currentNode = nextNode;
        }

        while (queue.size() + 2 > k) {
            DLList<Point>.Node removed = queue.remove();
            DLList<Point>.Node prev  = removed.prev;
            DLList<Point>.Node next = removed.next;

            points.remove(removed);

            // Update the value of prev if it isn't the first point in the list
            if (prev.prev != null) {
                queue.remove(prev);
                queue.add(prev);
            }

            // Update the value of next if it isn't the last point in the list
            if (next.next != null) {
                queue.remove(next);
                queue.add(next);
            }
        }

        // Transform the remaining points to an array of coordinates
        double[] newPoly = new double[k * 2];
        int i = 0;
        while (points.getFirst() != null) {
            DLList<Point>.Node p = points.getFirst();
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

/**
 * Compares two nodes in a DLList of points. This comparator calculates the
 * importance by looking at the previous and next nodes in the list, so it is
 * required that there is a previous and next node in the list prior to adding
 * a node to the priority queue.
 */
class PointComparator implements Comparator<DLList<Point>.Node> {
    @Override
    public int compare(DLList<Point>.Node p1, DLList<Point>.Node p2) {
        return Double.compare(
            p1.getValue().calcImportance(p1.prev.getValue(), p1.next.getValue()),
            p2.getValue().calcImportance(p2.prev.getValue(), p2.next.getValue())
        );
    }
}

class Point {
    private double x;
    private double y;

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

    /**
     * Calculates the importance of this point in relation to two other points.
     * @param l The first point to compare to.
     * @param r The second point to compare to.
     * @return the total distance between this point and l and r minus the
     *         distance between l and r.
     */
    public double calcImportance(Point l, Point r) {
        if (l == null || r == null) {
            throw new IllegalArgumentException("setImportance: null values not permitted");
        }

        double l1 = calcDist(l, this);
        double l2 = calcDist(this, r);
        double l3 = calcDist(l, r);
        return l1 + l2 - l3;
    }

    /**
     * Calculates the distance between two points.
     *
     * @param p1
     * @param p2
     * @return the distance between p1 and p2.
     */
    private static double calcDist(Point p1, Point p2) {
        double distX = Math.abs(p1.getX() - p2.getX());
        double distY = Math.abs(p1.getY() - p2.getY());
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
