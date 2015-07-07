package com.gmail.miv.task3;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Serializer {

    private String filePath;

    public Serializer(String filePath) {
        this.filePath = filePath;
    }

    public void writeObject(Object o) {

        Class<?> cls = o.getClass();
        String result = "ClassName:" + cls.getName() + "\n";

        try {

            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    String name = field.getAnnotation(Save.class).name();
                    if (name.equals("")) {
                        name = field.getName();
                    }
                    result += name + ":" + field.get(o) + "\n";
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(new File(filePath));
            fw.write(result);
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public Object readObject() {

        Object object = null;

        Map<String, String> map = getMapFromFile();

        try {

            Class cls = Class.forName(map.get("ClassName"));
            Constructor<?> ctr = cls.getConstructor();
            object = ctr.newInstance();

            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    String name = field.getAnnotation(Save.class).name();
                    if (name.equals("")) {
                        name = field.getName();
                    }

                    if (field.getType().equals(Integer.class)) {
                        field.set(object, Integer.valueOf(map.get(name)).intValue());
                    }

                    if (field.getType().equals(String.class)) {
                        field.set(object, map.get(name));
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return object;
    }

    private Map<String, String> getMapFromFile() {

        Map<String, String> map = new HashMap<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(new File(filePath)));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(":");
                map.put(tokens[0], tokens[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return map;
    }

}
