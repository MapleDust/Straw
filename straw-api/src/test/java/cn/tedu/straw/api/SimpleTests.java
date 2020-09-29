package cn.tedu.straw.api;

public class SimpleTests {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (long i = 0; i < 3000000000L; i++) {

        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
