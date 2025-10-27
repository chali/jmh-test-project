package org.example.matrix;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class MatrixMultiplication {

    private static final int SIZE = 1_000;
    private static final float[] a = new float[SIZE];
    private static final float[] b = new float[SIZE];

    @Benchmark
    public void test(Blackhole bh) {
        // Warm up to trigger JIT
        for (int i = 0; i < SIZE; i++) {
            a[i] = i;
            b[i] = i * 2;
        }
        bh.consume(baseline(a, b, (int )Math.sqrt(SIZE)));
    }

    public static float[] baseline(float[] a, float[] b, int n) {
        float[] c = new float[n * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                float sum = 0.0f;
                for (int k = 0; k < n; k++) {
                    sum += a[i * n + k] * b[k * n + j];
                }
                c[i * n + j] = sum;
            }
        }
        return c;
    }
}
