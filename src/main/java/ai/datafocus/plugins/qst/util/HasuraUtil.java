package ai.datafocus.plugins.qst.util;

import ai.datafocus.plugins.qst.dto.HasuraResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HasuraUtil {

  @Inject ObjectMapper jsonMapper;

  public HasuraResponse parse(String body) throws JsonMappingException, JsonProcessingException {
    return jsonMapper.readValue(body, HasuraResponse.class);
  }

  @SuppressWarnings("unchecked")
  public void alterValueAt(
      Map<String, Object> origin, Object value, String... keys) {
    Map<String, Object> tmp = origin;
    String[] excludeLastKey = Arrays.copyOf(keys, keys.length - 1);
    String lastKey = keys[keys.length - 1];
    for (String key : excludeLastKey) {
      tmp = (Map<String, Object>) tmp.get(key);
    }
    tmp.put(lastKey, value);
  }
}
