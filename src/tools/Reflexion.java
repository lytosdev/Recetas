package tools;

public class Reflexion {

  public static <T> T getInstancia(Class<T> clazz) {

    try {

      return clazz.getDeclaredConstructor().newInstance();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

}
