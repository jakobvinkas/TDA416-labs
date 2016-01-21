import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by maxim on 2016-01-19.
 */
public class Uppg1Test {
    public static final double eps = Math.pow(10, -1);
    @Test
    public void testStuff(){
        double n1 = Uppg1.binarySqrt(82918.12386987, eps);
        Assert.assertTrue(n1 >= Math.sqrt(82918.12386987) - eps && n1 <= Math.sqrt(82918.12386987) + eps);

        double n2 = Uppg1.binarySqrt(7, eps);
        Assert.assertTrue(n2 >= 2.645751311064590590501615753639260425710259183082450180368334 - eps && n2 <= 2.645751311064590590501615753639260425710259183082450180368334 + eps);

        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            double n = r.nextDouble() * 100;
            try {
                testSqrt(n, i);
            } catch (IllegalArgumentException e) {
                Assert.assertTrue(n < 1);
            }
        }
    }

    private void testSqrt(double n, int i) {
        System.out.println("time = " + i + ", n = " + n);
        double result = Uppg1.binarySqrt(n, eps);
        double actual = Math.sqrt(n);
        Assert.assertTrue("n = " + n + ", result = " + result + ", actual = " + actual + ", time = " + i, (result > (actual - eps)) && (result < (actual + eps)));
    }
}