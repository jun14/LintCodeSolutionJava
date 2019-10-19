package trailingzeros;

import java.util.HashMap;
import java.util.stream.LongStream;

/**
 * 5! = 1 * 2 * 3 * 4 * 5 = 120
 * 13! = 1 2 3 4 5 6 7 8 9 10 11 12 13
 * 数一数有多少个5和2，取他们之间的较小值
 */
public class Solution {
    private static HashMap<Long, Integer> fives = new HashMap<Long, Integer>();
//    private static HashMap<Long, Integer> twos = new HashMap<Long, Integer>();

    private static int count5s(long n) {
        Integer n5 = fives.get(n);
        if (n5 != null) {
            return n5;
        }
        n5 = 0;
        while (n >= 5 && n % 5 == 0) {
            n /= 5;
            n5++;
        }
        fives.put(n, n5);
        return n5;
    }

//    private static int count2s(long n) {
//        Integer n2 = twos.get(n);
//        if (n2 != null) {
//            return n2;
//        }
//        n2 = 0;
//        while (n >= 2 && n % 2 == 0) {
//            n /= 2;
//            n2++;
//        }
//        twos.put(n, n2);
//        return n2;
//    }

    public static long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
//        int n2 = 0;
        int n5 = 0;
        for (long i = 1; i <= n; i++) {
//            n2 += count2s(i);
            n5 += count5s(i);
        }
//        return Math.min(n2, n5);
        return n5;
    }

    public static void main(String[] args) {
        long[] testCase = {
                105,
                0xFF,
                100,
                128,
                512,
                1001171717};
//                1000_0000,
//                1000_0000_0000L,
//                1001_6688L,
//                Long.MAX_VALUE,
//                1000_1231_1000L};
        LongStream.of(testCase).forEach(it -> {
            System.out.printf("\t%d has %d zero(s)\n",
                    it, trailingZeros(it));
        });
    }
}
