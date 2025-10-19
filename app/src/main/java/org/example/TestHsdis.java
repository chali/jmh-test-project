package org.example;

public class TestHsdis {

    private static final int SIZE = 1_000_000;
    private static final float[] a = new float[SIZE];
    private static final float[] b = new float[SIZE];
    private static final float[] c = new float[SIZE];

    public static void main(String[] args) {
        // Warm up to trigger JIT
        for (int i = 0; i < SIZE; i++) {
            a[i] = i;
            b[i] = i * 2;
        }

        for (int r = 0; r < 10_000; r++) {
            addArrays();
        }

        // Print sample
        System.out.println(c[12345]);
    }

    private static void addArrays() {
        for (int i = 0; i < SIZE; i++) {
            c[i] = a[i] + b[i];
        }
    }

}