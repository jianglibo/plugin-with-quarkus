package ai.datafocus.plugins.qst;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

public class CommonTypeReferences {

  public static final TypeReference<Map<String, Object>> string2object =
      new TypeReference<Map<String, Object>>() {};
}
