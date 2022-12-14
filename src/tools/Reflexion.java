package tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflexion {

	public static <T> T getInstancia(Class<T> clazz) {

		try {

			return clazz.getDeclaredConstructor().newInstance();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void setValorMetodo(Object objeto, String metodo, Object valor) {

		try {

			metodo = "set" + metodo;
			Method method = getMethod(objeto, metodo);

			method.invoke(objeto, valor);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void setValorCampo(Object objeto, String campo, Object valor) {

		try {

			Field field = getField(objeto, campo);

			field.setAccessible(true);
			field.set(objeto, valor);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private static Method getMethod(Object objeto, String metodo) {

		Method[] methods = objeto.getClass().getDeclaredMethods();

		for (Method m : methods) {
			if (m.getName().equalsIgnoreCase(metodo)) {
				return m;
			}
		}

		return null;
	}

	private static Field getField(Object objeto, String campo) {

		Field[] fields = objeto.getClass().getDeclaredFields();

		for (Field f : fields) {
			if (f.getName().equalsIgnoreCase(campo)) {
				return f;
			}
		}

		// TODO: Implementar no sólo para la clase padre sino para
		// todas las clases padre haciendo un bucle
		fields = objeto.getClass().getSuperclass().getDeclaredFields();

		for (Field f : fields) {
			if (f.getName().equalsIgnoreCase(campo)) {
				return f;
			}
		}

		return null;
	}

}
