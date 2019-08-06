package trailingzeros;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchmarkUtil {

    private static final long testValue = 1000_1231_8880_1000L;

    private static final Runnable runnable = () -> {
        TrailingZeroUtil.countByMode(testValue);
    };

    @Benchmark
    public void measureCountByMode() {
        TrailingZeroUtil.countByMode(testValue); // 0.473 ± 0.011
    }

    @Benchmark
    public void measureCountByModeInRunnable() {
        runnable.run();
    }

    @Benchmark
    public void measureCountInStr() {
        TrailingZeroUtil.countInStr(testValue); // 37.160 ± 2.738 ns
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkUtil.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
