package trailingzeros;

import java.util.HashMap;
import java.util.function.LongConsumer;

public class TrailingZeroUtil {
    private static final long ZERO16 = 1_0000_0000_0000_0000L;
    private static final long ZERO8 = 1_0000_0000L;
    private static final long ZERO4 = 1_0000L;
    private static final long ZERO2 = 100L;
    private static final long ZERO1 = 10L;

    private static int countByMode(long value) {
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
//                value /= ZERO1;
                n += 1;
            }
        }
        return n;
    }

    private static int countInStr(long value) {
        String s = String.valueOf(value);
        int idx = s.lastIndexOf("0");
        if (idx == -1) {
            return 0;
        }
        if (idx + 1 < s.length()) { // not the last char
            return 0;
        }
        idx = s.indexOf("0"); // fixme: not correct answer! wrong if value = 1000_1231_1000L
        return s.length() - idx;
    }

    private static void run(long[] testCase, LongConsumer calFunc, int times) {
        for (int i = 0; i < times; i++) {
            for (long aTestCase : testCase) {
                calFunc.accept(aTestCase);
            }
        }
    }

    public static void main(String[] args) {
        long[] testCase = {0xFF, 100, 128, 512, 1000_0000, 1000_0000_0000L, 1001_6688L, Long.MAX_VALUE, 1000_1231_1000L};
        System.out.println("count by mode:");
        run(testCase, i -> {
            System.out.printf("\t%d has %d zero(s)\n", i, countByMode(i));
        }, 1);
        System.out.println("count in str:");
        run(testCase, i -> {
            System.out.printf("\t%d has %d zero(s)\n", i, countInStr(i));
        }, 1);

        long fives = 5;
        long i = fives;
        long powerTimes = 1;

        while (i*5 > i) {
            i *= fives;
            System.out.println(i);
        }

        System.out.println(131 / 5);
    }
}



