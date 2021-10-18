package ai.datafocus.plugins.qst;

import java.io.IOException;
import java.io.InputStream;

public class UtilFort {

  public static String loadFromResource(String path) throws IOException {
    ClassLoader classLoader = UtilFort.class.getClassLoader();

    try (InputStream is = classLoader.getResourceAsStream(path)) {
      return new String(is.readAllBytes());
    }
  }

  public static void out(Object o) {
    System.out.println(o);
  }
}
