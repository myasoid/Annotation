package com.gmail.miv.task4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.*;
import java.util.*;

public class MyGson {

    public String toJson(Object o) {

        String result = "";

        result = "{";
        Class<?> cls = o.getClass();

        Set types = getPrimitiveTypes();

        try {

            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                if (!(Modifier.isTransient(field.getModifiers()))) {

                    if (types.contains(field.getType())) {

                        result += "\"" + field.getName() + "\":" + getFieldValueToJson(field.get(o)) + ",";

                    } else if (field.getType().isArray()) {
                        result += "\"" + field.getName() + "\":[";

                        Object value = field.get(o);
                        Object[] boxedArray = new Object[Array.getLength(field.get(o))];
                        for (int i = 0; i < Array.getLength(value); i++) {

                            Type componentType = field.getType().getComponentType();

                            if (types.contains(componentType)) {
                                result += "\"" + field.getName() + "\":" + getFieldValueToJson(Array.get(value, i)) + ",";
                            } else {
                                result += toJson(Array.get(value, i)) + ",";
                            }
                        }
                        result = result.substring(0, result.length() - 1);
                        result += "],";

                    } else {

                        result += toJson(field.get(o));

                    }
                }
            }
            result = result.substring(0, result.length() - 1);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        result += "}";

        return result;

    }

    public Object fromJson(String string, Class cls) {

        Object o = null;

        try {
            JSONObject jsonObject = new JSONObject(string);
            o = fromJson(jsonObject, cls);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return o;

    }

    public Object fromJson(JSONObject jsonObject, Class cls) {

        Object o = null;
        try {

            Constructor<?> ctr = cls.getConstructor();
            o = ctr.newInstance();

            Set types = getPrimitiveTypes();

            Iterator<?> it = jsonObject.keys();

            while (it.hasNext()) {

                String key = (String) it.next();
                Field field = cls.getDeclaredField(key);
                field.setAccessible(true);
                Type fieldType = field.getType();

                if (types.contains(fieldType)) {
                    field.set(o, getFieldValueFromJson(jsonObject, key, field.getType()));
                } else {

                    if (field.getType().isArray()) {
                        JSONArray jsonArr = jsonObject.getJSONArray(key);

                        Class<?> c = field.getType().getComponentType();
                        int n = jsonArr.length();

                        Object arr = Array.newInstance(c, n);
                        for (int i = 0; i < n; i++) {
                            Object val = fromJson(jsonArr.getJSONObject(i), c);
                            Array.set(arr, i, val);
                        }
                        field.set(o, arr);

                    } else {
                        field.set(o, fromJson(jsonObject.getJSONObject(key), field.getType()));
                    }
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return o;

    }

    private Set<Class<?>> getPrimitiveTypes() {

        Set<Class<?>> set = new HashSet<>();

        set.add(Integer.class);
        set.add(int.class);
        set.add(Double.class);
        set.add(double.class);
        set.add(Boolean.class);
        set.add(boolean.class);
        set.add(String.class);

        return set;

    }

    private String getFieldValueToJson(Object o) {
        String result = "";
        if (o.getClass().equals(String.class)) {
            result = "\"" + o.toString() + "\"";
        } else {
            result = o.toString();
        }
        return result;
    }

    private Object getFieldValueFromJson(JSONObject jsonObject, String key, Class cls) {

        Object result = null;
        try {
            if (cls.equals(String.class)) {

                result = jsonObject.getString(key);

            } else if (cls.equals(Integer.class) || cls.equals(int.class)) {
                result = jsonObject.getInt(key);
            } else if (cls.equals(Double.class) || cls.equals(double.class)) {
                result = jsonObject.getDouble(key);
            } else if (cls.equals(Boolean.class) || cls.equals(boolean.class)) {
                result = jsonObject.getBoolean(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


}
