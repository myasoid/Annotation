package com.gmail.miv.task1;

public class OtherClass {

    public static String work(String... ls) {

        StringBuilder sb = new StringBuilder();
        for (String s : ls)
            sb.append(s);

        return sb.toString();

    }

    @Test(a = 2, b = 5)
    public static boolean testMethod(int a, int b) {
        boolean res = work("1", "22", "333").equals("122333");
        System.out.println("OtherClass: " + res);
        return res;
    }
}