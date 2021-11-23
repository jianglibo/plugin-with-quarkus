package ai.datafocus.plugins.qst.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ai.datafocus.plugins.qst.MyConfig;

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
}
