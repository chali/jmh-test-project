package org.example.matrix;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class Multiplication {
    private int number;
    private final Random random = new Random(12345); // fixed seed for reproducibility

    @Setup(Level.Trial)
    public void setup() {
        number = random.nextInt();
    }

    @Benchmark
    public int test() {
        //return baseline();
        return methodCall();
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int methodCall() {
        int result = number;
        for (int i = 0; i < number; i++) {
            result = result * number;
        }
        return result;
    }
}
