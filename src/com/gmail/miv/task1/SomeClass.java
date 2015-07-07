package com.gmail.miv.task1;

public class SomeClass {
    public static long job(long... ls) {
        long res = 0;
        for (long l : ls)
            res += l;

        return res;
    }

    @Test(a = 2, b = 5)
    public static boolean selfTest(int a, int b) {
        boolean res = job(1, 2, 3, 4) == 10;
        System.out.println("SomeClass: " + res);
        return res;
    }
}
