package trailingzeros;

import java.util.Arrays;
import java.util.stream.LongStream;

/**
 * 使用Mod的方法，遍历1～x（x是阶乘的终点🏁）
 * 每次乘法之后，除掉后面的0
 * <p>
 * 问题：这么做容易overflow了
 */
public class SolutionByMod {
    private static final long ZERO16 = 1_0000_0000_0000_0000L;
    private static final long ZERO8 = 1_0000_0000L;
    private static final long ZERO4 = 1_0000L;
    private static final long ZERO2 = 100L;
    private static final long ZERO1 = 10L;

    public static class ProductAndZeros {
        public long Product = 1L;
        public long zeros = 0L;
    }

    private static int countByMode(ProductAndZeros pz) {
        long value = pz.Product;
        if (value == 0L) {
            return 1;
        }
        long y = 0L;
        value = Math.abs(value);
        int n = 0;
        if (value >= ZERO16) {
            y = value % ZERO16;
            if (y == 0L) {
                value /= ZERO16;
                n += 16;
            }
        }
        if (value >= ZERO8) {
            y = value % ZERO8;
            if (y == 0L) {
                value /= ZERO8;
                n += 8;
            }
        }
        if (value >= ZERO4) {
            y = value % ZERO4;
            if (y == 0L) {
                value /= ZERO4;
                n += 4;
            }
        }
        if (value >= ZERO2) {
            y = value % ZERO2;
            if (y == 0L) {
                value /= ZERO2;
                n += 2;
            }
        }
        if (value >= ZERO1) {
            y = value % ZERO1;
            if (y == 0L) {
                value /= ZERO1;
                n += 1;
            }
        }
        pz.Product = value;
        pz.zeros += n;
        return n;
    }

    public static long trailingZeros(long v) {
        if (v == 1L) {
            return 0L;
        }
        ProductAndZeros pz = new ProductAndZeros();
        for (long i = 2; i <= v; i++) {
            pz.Product *= i;
            long n = countByMode(pz);
//            System.out.println(n);
        }
        return pz.zeros;
    }

    public static void main(String[] args) {
        long[] testCase = {
                0xFF,
                100,
                128,
                512,
                1000_0000,
                1000_0000_0000L,
                1001_6688L,
                Long.MAX_VALUE,
                1000_1231_1000L};
        LongStream.of(testCase).forEach(it -> {
            System.out.printf("\t%d has %d zero(s)\n",
                    it, trailingZeros(it));
        });
    }
}
