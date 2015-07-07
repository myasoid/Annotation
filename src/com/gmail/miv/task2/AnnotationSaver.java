package com.gmail.miv.task2;


import java.lang.reflect.Method;

public class AnnotationSaver {

    public static void save(Object o) {

        try {
            Class<?> cls = o.getClass();
            if (cls.isAnnotationPresent(SaveTo.class)) {
                SaveTo saveTo = cls.getAnnotation(SaveTo.class);
                Method[] methods = cls.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Saver.class)) {
                        method.invoke(o, saveTo.fileName());
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
