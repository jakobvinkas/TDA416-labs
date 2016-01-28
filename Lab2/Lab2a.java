public class Lab2a {
  public static double[] simplifyShape(double[] poly, int k)
  {
      // TODO
  }


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
  private static double calcDist(double x1, double y1, double x2, double y2){
    double distX = Math.abs(x1-x2);
    double distY = Math.abs(y1-y2);
    return Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2));
  }
}
