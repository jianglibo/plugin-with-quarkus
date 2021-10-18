package ai.datafocus.plugins.qst.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.datafocus.plugins.qst.MyConfig;
import ai.datafocus.plugins.qst.dto.OutOfPlugin;

@Singleton
public class AppUtil {

  @Inject MyConfig myconfig;

  @Inject ObjectMapper mapper;

  public void printOutData(String data) {
    System.out.println();
    System.out.println(myconfig.getSeparator());
    System.out.println(data);
    System.out.println(myconfig.getSeparator());
    System.out.println();
  }

  public void printOutData(OutOfPlugin data) throws JsonProcessingException {
    printOutData(mapper.writeValueAsString(data));
  }
}
