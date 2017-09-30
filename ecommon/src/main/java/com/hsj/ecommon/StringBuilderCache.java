package com.hsj.ecommon;

public class StringBuilderCache {
    private static StringBuilder cache = new StringBuilder(256);

    private static final int MAX_BUILDER_SIZE = 512;

    public static StringBuilder Acquire() {
        return Acquire(256);
    }

    public static StringBuilder Acquire(int capacity) {
        StringBuilder sb = cache;
        if (sb != null && sb.capacity() >= capacity) {
            cache = null;
            sb.setLength(0);
            return sb;
        }
        return new StringBuilder(capacity);
    }

    public static String GetStringAndRelease(StringBuilder sb) {
        String ret = sb.toString();

        return ret;
    }

    public static void Release(StringBuilder sb) {
        if (sb.capacity() <= MAX_BUILDER_SIZE) {
            cache = sb;
        }
    }
}
