package com.gmail.miv.task2;

/*Написать класс TextContainer, который содержит в себе строку. С помощью механизма аннотаций указать
        1) в какой файл должен сохраниться текст
        2) метод, который выполнит сохранение. Написать класс Saver,
        который сохранит объект класса TextContainer.
            @SaveTo(“c:\\file.txt”)
            class Container {
            ...
            @Saver
            public void save(..) {...}
            }*/

public class Main {

    public static void main(String[] args) {

        AnnotationSaver.save(new TextContainer("Some text in TextContainer"));

    }
}
