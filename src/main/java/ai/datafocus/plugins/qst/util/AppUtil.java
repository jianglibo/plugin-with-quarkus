package ai.datafocus.plugins.qst.util;

import ai.datafocus.plugins.qst.MyConfig;
import ai.datafocus.plugins.qst.dto.MockRow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppUtil {

  @Inject MyConfig myconfig;

  @Inject ObjectMapper mapper;

  public void printOutDataString(String data) {
    System.out.println();
    System.out.println(myconfig.getSeparator());
    System.out.println(data);
    System.out.println(myconfig.getSeparator());
    System.out.println();
  }

  public void printOutDataJson(Object data) throws JsonProcessingException {
    printOutDataString(mapper.enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(data));
  }

  public static String randomString(int targetStringLength) {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    Random random = new Random();

    return random
        .ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  public static MockRow genRandomRow(int id, int nameLength) {
    return MockRow.builder()
        .id(id)
        .age(ThreadLocalRandom.current().nextInt(120))
        .name(AppUtil.randomString(nameLength))
        .build();
  }
}
