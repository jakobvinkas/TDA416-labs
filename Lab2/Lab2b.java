import java.util.PriorityQueue;

public class Lab2b {
    public static double[] simplifyShape(double[] poly, int k) {
        PriorityQueue<Node<Point>> queue = new PriorityQueue<>();
        DLList<Point> points = new DLList<>();
        Point previous = new Point(poly[0], poly[1]);
        Point current = new Point(poly[2], poly[3]);
        DLList.Node<Point> currNode = points.addFirst(previous);
        currNode = points.insertAfter(current, currNode);
        for (int i = 4; i<poly.length; i+=2 ) {
            Point next = new Point(poly[i], poly[i+1]);
            current.setImportance(previous, next);
            queue.add(currNode);
            currNode = points.insertAfter(next, currNode);
            previous = current;
            current = next;
        }

        while (queue.size() > k) {
            DLList.Node<Point> removed = queue.remove();
            DLList.Node<Point> prev  = removed.prev;
            DLList.Node<Point> next = removed.next;
            points.remove(removed);
            prev.getValue().setImportance(prev.prev.getValue(), next.getValue());
            next.getValue().setImportance(prev.getValue(), next.next.getValue());
            queue.remove(prev);
            queue.remove(next);
            queue.add(prev);
            queue.add(next);
        }
        ArrayList<Double> newPoly = new ArrayList<>();

        while(points.getFirst() != null) {
            DLList.Node<Point> p = points.getFirst();
            newPoly.add(p.getValue().getX());
            newPoly.add(p.getValue().getY());
            points.remove(p);
        } 
        return newPoly.toArray();     
    }

    private static void updateNodes(DLList.Node<Point> node) {

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
}
