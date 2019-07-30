package aplusb;

public class Solution {
    public static int aplusb(int a, int b) {
        int sum, addon;
        do {
            sum = a ^ b;
            addon = (a & b) << 1;
            a = sum;
            b = addon;
        } while (addon != 0);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(aplusb(10, 2));
        System.out.println(aplusb(10, -2));
    }
}
