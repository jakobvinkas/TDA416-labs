package Lab1;

/**
 * Created by maxim on 2016-01-19.
 */
public class MySqrt {

    private static final double EPS = Math.pow(10, -6);

    /**
     * Calculates an approximation of the square root of x. This method does this by taking a recursive approach.
     *
     * @param x the number to find the square root of.
     * @param epsilon the allowed error tolerance.
     * @return an approximation of the square root of x, that is less than epsilon from the correct square root.
     */
    public static double mySqrtRecurse(double x, double epsilon) {
        if (x < 0){
            return Double.NaN;
        } else if (x == 0) {
            return 0;
        } else if (x <= 1) {
            return mySqrtRecurseHelper(x, epsilon, x, 1);
        } else {
            return mySqrtRecurseHelper(x, epsilon, 1, x);
        }
    }

    /**
     * Calculates an approximation of the square root of x. It does this by looking at the number in the middle of
     * low and high. If this
     *
     * @param x
     * @param epsilon
     * @param low
     * @param high
     * @return
     */
    private static double mySqrtRecurseHelper(double x, double epsilon, double low, double high){
        // Calculates the mean value isBetween high and low.
        double n = (high + low) / 2;

        if (isBetween(square(n), x - epsilon, x + epsilon)) {
            /* If n^2 is isBetween x - epsilon and x + epsilon we have found a number that is sufficiently close to the
               real root.

               For more precision, you could check if (n - epsilon)^2 is smaller than our x, and
               (n + epsilon)^2 is larger than our x. This would give you a number that is less than epsilon from the
               real root, and not a number that squared is less than epsilon from the starting number. */
            return n;
        } else if (square(n) > x) {
            // If n^2 is larger than x, we know that the real root is somewhere isBetween low and n.
            return mySqrtRecurseHelper(x, epsilon, low, n);
        } else {
            // If n^2 is smaller than x, we know that the real root is somewhere isBetween n and high.
            return mySqrtRecurseHelper(x, epsilon, n, high);
        }
    }

    /**
     * Calculates an approximation of the square root of x. This method does the exact same thing as mySqrtRecurse,
     * but it does it iteratively instead.
     *
     * @param x the number to find the square root of.
     * @param epsilon the allowed error tolerance.
     * @return an approximation of the square root of x, that is less than epsilon from the correct square root.
     */
    public static double mySqrtLoop(double x, double epsilon) {
        if (x < 0){
            return Double.NaN;
        } else if (x == 0) {
            return 0;
        }

        double low, high;

        if (x <= 1) {
            low = x;
            high = 1;
        } else {
            low = 1;
            high = x;
        }

        double n;
        do {
            double offset = (high - low) / 2;
            n = low + offset;

            if (square(n) > x) {
                high = n;
            } else if (square(n) < x) {
                low = n;
            }
        } while (!isBetween(square(n), x - epsilon, x + epsilon));

        return n;
    }

    /**
     * Squares a number and returns the result.
     * @param x the number to square.
     * @return the square of x, x^2.
     */
    private static double square(double x) {
        return x * x;
    }

    /**
     * Determines if the number x is inside an interval given by two other numbers.
     *
     * @param x the number to check.
     * @param a the left side of the interval.
     * @param b the right side of the interval.
     * @return true if x is between a and b, otherwise false.
     */
    private static boolean isBetween(double x, double a, double b) {
        return x >= a && x <= b;
    }

    public static void main(String[] args) {
        testRecursiveSqrt(123101.012034);
        testRecursiveSqrt(7);
        testRecursiveSqrt(0.9);
        testRecursiveSqrt(0.0001);
        testRecursiveSqrt(-1);

        testLoopSqrt(123101.012034);
        testLoopSqrt(7);
        testLoopSqrt(0.9);
        testLoopSqrt(0.0001);
        testLoopSqrt(-1);
    }

    private static void testRecursiveSqrt(double x) {
        double result = MySqrt.mySqrtRecurse(x, EPS);
        testResult(x, result);
    }

    private static void testLoopSqrt(double x) {
        double result = MySqrt.mySqrtLoop(x, EPS);
        testResult(x, result);
    }

    /**
     * We are using Math.sqrt() to test if our methods are correctly implemented.
     *
     * @param x The number we want to square
     * @param result The result we want to test
     */
    private static void testResult(double x, double result) {
        boolean correct;
        if (x < 0) {
            correct = Double.isNaN(result);
        } else {
            correct = isBetween(square(result), x - EPS, x + EPS);
        }
        System.out.println("x = " + x + ", result = " + result + " result^2 = " + square(result) + ", correct = " + correct);
    }
}
