package com.gmail.miv.task3;


import java.util.Date;

public class SomeClass {

    @Save
    private Integer someInt;
    @Save(name = "AliasSomeString")
    public String someString;

    private Date someDate;

    // needs constructor without params for my Serializer
    public SomeClass() {
    }

    public SomeClass(int someInt, String someString, Date someDate) {
        this.someInt = someInt;
        this.someString = someString;
        this.someDate = someDate;
    }

    @Override
    public String toString() {
        return "SomeClass{" +
                "someInt=" + someInt +
                ", someString='" + someString + '\'' +
                ", someDate=" + someDate +
                '}';
    }

}
