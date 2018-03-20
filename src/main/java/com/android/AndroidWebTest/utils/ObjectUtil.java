package com.android.AndroidWebTest.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectUtil {

    /**
     * 获取对象的属性和属性值
     *
     * @param entityName
     * @return
     */
    public static LinkedHashMap<String, String> getProperty(Object entityName) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        Class aClass = entityName.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            Object o = null;
            try {
                o = invokeMethod(entityName, field.getName(), null);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(field.getName() + "  " + o.toString());
            map.put(field.getName(), o.toString());
        }
        return map;
    }

    /**
     * 获取对象的属性值
     *
     * @param entityName
     * @param name
     * @param o
     * @return
     */
    private static Object invokeMethod(Object entityName, String name, Object[] o) throws InvocationTargetException,
            IllegalAccessException {

        Class entityNameClass = entityName.getClass();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        Method method = null;
        try {
            method = entityNameClass.getMethod("get" + name);
        } catch (NoSuchMethodException e) {
            return "con't find" + name + "' method";
        }
        return method.invoke(entityName);

    }
}
