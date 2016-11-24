package network.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import network.exceptions.ReflectionFoundNoMatchesException;

/**
 * Utilities for using reflection
 * @author CharlesXu
 */
public class ReflectionUtils {
	
	public static Object newInstanceOf(String className, Object... params) 
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, ReflectionFoundNoMatchesException {
		Class<?> c = Class.forName(className);
		Constructor<?> constructor = ReflectionUtils.getConstructor(c, params);
		return constructor.newInstance(params);
	}

	public static Constructor<?> getConstructor(Class<?> c, Object... params)
			throws ReflectionFoundNoMatchesException {
		Class<?>[] callerParamTypes = toClassArray(params);
		Constructor<?>[] constructors = c.getConstructors();
		for (Constructor<?> con : constructors) {
			Class<?>[] constructorParamTypes = con.getParameterTypes();
			if (paramListMatches(callerParamTypes, constructorParamTypes))
				return con;
		}
		throw new ReflectionFoundNoMatchesException(c.getCanonicalName());
	}

	public static boolean paramListMatches (Class<?>[] c1, Class<?>[] c2) {
        if (c1.length != c2.length)
        	return false;
        for (int i = 0; i < c1.length; i++) {
            boolean match = c1[i].isAssignableFrom(c2[i]) || c2[i].isAssignableFrom(c1[i]);
            if (!match) return false;
        }
        return true;
    }
	
	public static Class<?>[] toClassArray(Object... params) {
		Class<?>[] types = new Class<?>[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}
		return types;
	}
}
