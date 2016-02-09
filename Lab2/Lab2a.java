public class Lab2a {
    public static double[] simplifyShape(double[] poly, int k) {
        while (poly.length > k * 2) {
            double minImportance = -1;
            int minImportanceIndex = -1;

            // Find least important point
            for (int i = 2; i < poly.length - 2; i += 2) {
                double importance = calcImportance(poly[i - 2], poly[i - 1],
                        poly[i], poly[i + 1], poly[i + 2], poly[i + 3]);
                if (minImportance == -1 || importance <= minImportance) {
                    minImportance = importance;
                    minImportanceIndex = i;
                }
            }

            // Remove least important point from array. We do this by creating
            // a new array and copying all elements except the least important
            // one
            double[] tmp = new double[poly.length - 2];
            int tmpIndex = 0;
            for (int i = 0; i < poly.length; i += 2) {
                if (i != minImportanceIndex) {
                    tmp[tmpIndex] = poly[i];
                    tmp[tmpIndex + 1] = poly[i + 1];
                    tmpIndex += 2;
                }
            }
            poly = tmp;
        }

        return poly;
    }

    /**
     * Calculates the importance of a single point.
     *
     * @param x1 The x coordinate of the previous point.
     * @param y1 The y coordinate of the previous point.
     * @param x2 The x coordinate of the point to calculate the importance for.
     * @param y2 The y coordinate of the point to calculate the importance for.
     * @param x3 The x coordinate of the next point.
     * @param y3 The y coordinate of the next point.
     * @return The importance of the point.
     */
    private static double calcImportance(double x1, double y1, double x2, double y2, double x3, double y3){
        double l1 = calcDist(x1, y1, x2, y2);
        double l2 = calcDist(x2, y2, x3, y3);
        double l3 = calcDist(x1, y1, x3, y3);
        return l1+l2-l3;
    }


    /**
     * Uses pythagorean theorem
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    private static double calcDist(double x1, double y1, double x2, double y2) {
        double distX = Math.abs(x1-x2);
        double distY = Math.abs(y1-y2);
        return Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2));
    }
}
