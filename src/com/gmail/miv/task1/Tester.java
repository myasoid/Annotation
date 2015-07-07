package com.gmail.miv.task1;

import java.lang.reflect.Method;

public class Tester {
	public static boolean test(Class<?>... ls) {
		try {
			for (Class<?> cls : ls) {
				Method[] methods = cls.getDeclaredMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(Test.class)) {
						Test testAnnotation = method.getAnnotation(Test.class);
						Boolean b = (Boolean) method.invoke(null, testAnnotation.a(), testAnnotation.b());
						if ( ! b)
							return false;
					}
				}
			}
			
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
