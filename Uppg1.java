/**
 * Created by maxim on 2016-01-19.
 */
public class Uppg1 {

    public static double binarySqrt(double sqrt, double eps){
        if(sqrt < 1){
            throw new IllegalArgumentException("Shit sucks");
        }
        return help(sqrt, eps, 1, sqrt);
    }

    public static double help(double sqrt, double eps, double low, double high){
        double offset = (high - low)/2;
        double n = low+offset;
        double nminuseps = n-eps;
        double npluseps = n+eps;
        if(nminuseps*nminuseps <= sqrt  && npluseps*npluseps >= sqrt){
            return n;
        }
        else if(n*n > sqrt){
            return help(sqrt, eps, low, n);
        }else{
            return help(sqrt, eps, n, high);
        }
    }
}
