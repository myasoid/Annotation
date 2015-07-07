package com.gmail.miv.task4;

/* Написать свой простой аналог библиотеки GSON.*/
public class Main {

    public static void main(String[] args) {

        // https://en.wikipedia.org/wiki/Gson
        // class car & person from this article

        Car audi = new Car("Audi", "A4", 1.8, false);
        Car skoda = new Car("Škoda", "Octavia", 2.0, true);
        Car[] cars = {audi, skoda};

        Person johnDoe = new Person("John", "Doe", 245987453, 35, cars);

        System.out.println(johnDoe);
        /*
        Name: John Doe
        Phone: 245987453
        Age: 35
        Car 1: Car{manufacturer='Audi', model='A4', capacity=1.8, accident=false}
        Car 2: Car{manufacturer='Škoda', model='Octavia', capacity=2.0, accident=true}
        */

        MyGson myGson = new MyGson();

        String json = myGson.toJson(johnDoe);
        System.out.println(json);
        // {"name":"John","surname":"Doe","cars":[{"manufacturer":"Audi","model":"A4","capacity":1.8,"accident":false},{"manufacturer":"Škoda","model":"Octavia","capacity":2.0,"accident":true}],"phone":245987453}
        System.out.println();

        Person johnDoeFromGson = (Person) myGson.fromJson(json, Person.class);

        System.out.println(johnDoeFromGson);
        /*
        Name: John Doe
        Phone: 245987453
        Age: 0
        Car 1: Car{manufacturer='Audi', model='A4', capacity=1.8, accident=false}
        Car 2: Car{manufacturer='Škoda', model='Octavia', capacity=2.0, accident=true}
        */

    }

}
