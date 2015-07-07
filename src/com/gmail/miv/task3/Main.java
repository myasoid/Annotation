package com.gmail.miv.task3;


import java.util.Date;

/* Написать код, который сериализирует и десериализирует в/из файла все значения полей класса,
которые отмечены аннотацией @Save.*/

public class Main {

    public static void main(String[] args) {

        Serializer serializer = new Serializer("src/com/gmail/miv/task3/SerializedData.txt");

        SomeClass someClass1 = new SomeClass(23, "Instance someClass", new Date());

        System.out.println(someClass1);
        // SomeClass{someInt=23, someString='Instance someClass', someDate=Tue Jul 07 01:13:16 EEST 2015}

        serializer.writeObject(someClass1);

        SomeClass someClass2 = (SomeClass) serializer.readObject();

        System.out.println(someClass2);
        // SomeClass{someInt=23, someString='Instance someClass', someDate=null}

    }
}
