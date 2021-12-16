package ai.datafocus.plugins.qst.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class HasuraResponse {

  private List<HasuraError> errors;

  private Map<String, Object> data;

  public boolean hasError() {
    return errors != null && errors.size() > 0;
  }

  @SuppressWarnings("unchecked")
  public Map<String, Object> oneResponse(String key) {
    return (Map<String, Object>) data.get(key);
  }

  public Map<String, Object> oneResponse() {
    if (data.keySet().size() == 1) {
      return oneResponse(data.keySet().iterator().next());
    } else {
      throw new RuntimeException(
          "I'm not sure which key to use. " + String.join(", ", data.keySet()));
    }
  }

  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> listResponse(String key) {
    return (List<Map<String, Object>>) data.get(key);
  }

  public List<Map<String, Object>> listResponse() {
    if (data.keySet().size() == 1) {
      return listResponse(data.keySet().iterator().next());
    } else {
      throw new RuntimeException(
          "I'm not sure which key to use. " + String.join(", ", data.keySet()));
    }
  }

  @Data
  public static class HasuraError {
    private Extension extensions;
    private String message;
  }

  @Data
  public static class Extension {
    private String path;
    private String code;
  }

  public void dumpErrors() {
    for (HasuraError error : errors) {
      System.out.println("***************************START ERRORS*************************");
      System.out.println(error);
      System.out.println("***************************END ERRORS*************************");
    }
  }

  public void dumpData() {
    System.out.println("***************************START DATA*************************");
    System.out.println(data);
    System.out.println("***************************END DATA*************************");
  }
}
