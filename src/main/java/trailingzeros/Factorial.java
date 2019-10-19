package trailingzeros;

/**
 * 	255 has 62 zero(s)
 * 100 has 72 zero(s)
 * 128 has 74 zero(s)
 * 512 has 171 zero(s)
 */
public class Factorial {
    private long dest = 1;

    public Factorial(long dest) {
        this.dest = dest;
    }

    public long cal() {
        long fac = 1;
        for (long i = 1; i <= this.dest; i++) {
            fac *= i;
        }
        return fac;
    }

    public static void main(String[] args) {
        System.out.println(new Factorial(100L).cal());
    }
}
