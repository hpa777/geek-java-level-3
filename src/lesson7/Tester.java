package lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;

public class Tester {

    public static void start(String className) {
        try {
            start(Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class cls) {
        Method before = null;
        Method after = null;
        TreeMap<Integer, ArrayList<Method>> map = new TreeMap<>();
        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (before == null) {
                    before = method;
                } else {
                    throw new RuntimeException("BeforeSuite может быть только один");
                }
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                if (after == null) {
                    after = method;
                } else {
                    throw new RuntimeException("AfterSuite может быть только один");
                }
            } else if (method.isAnnotationPresent(Test.class)) {
                int key = ((Test) method.getAnnotation(Test.class)).priority();
                ArrayList<Method> row = map.getOrDefault(key, new ArrayList<>());
                row.add(method);
                map.put(key, row);
            }
        }
        Object obj = null;
        try {
            obj = cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            if (before != null) {
                before.invoke(obj);
            }
            for (ArrayList<Method> row : map.values()) {
                for (Method method : row) {
                    method.invoke(obj);
                }
            }
            if (after != null) {
                after.invoke(obj);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
