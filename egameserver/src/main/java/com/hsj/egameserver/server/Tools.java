package com.hsj.egameserver.server;

public class Tools {
    public static long statCalc(long n, long count) {
        long coef = (n / count);
        return (long) ((0.5 * coef * (1 + coef)) * count) + (coef + 1) * (n % count);
    }

    public static <T extends Comparable<T>> T between(T current, T min, T max) {
        T value = current;
        if (value.compareTo(max) > 0) {
            value = max;
        } else if (value.compareTo(min) < 0) {
            value = min;
        }
        return value;
    }

    public static boolean successRateCalc(float limit) {
        return limit > Server.getRand().nextDouble();
    }
}
