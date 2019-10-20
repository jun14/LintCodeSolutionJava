package trailingzeros;

import java.util.stream.LongStream;

/**
 * 5! = 1 * 2 * 3 * 4 * 5 = 120
 * 25! = 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
 * 25 / 5 = 5
 * 25 / 25 = 1
 * 5 + 1 = 6
 * <p>
 * 130! = 5 10 15 20 25 30 35 40 45 50 55 60 65 70 ... 120 125 130
 * 130 / 5 = 26
 * 130 / 25 = 5.?
 * 130 / 125 = 1.?
 * 26 + 5 + 1 = 32
 * 数一数有多少个5和2，取他们之间的较小值
 */
public class Solution {

    public static long trailingZeros(long n) {
        long five = 5L;
        long zeros = 0L;
        while (n >= five
                && five * 5 > five) { // incase long value overflow
            zeros += n / five;
            five *= 5;
        }
        return zeros;
    }

    public static void main(String[] args) {
        long[] testCase = {
                105,
                0xFF,
                100,
                128,
                512,
                1001171717,
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
