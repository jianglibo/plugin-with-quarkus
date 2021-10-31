package ai.datafocus.plugins.qst.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class OutputType {
  private String output_type;
  private Map<String, Object> settings;

  public static OutputType defaultOutputType(String separator) {
    OutputType output = new OutputType();
    output.output_type = "STDIO";
    output.settings = new HashMap<String, Object>();
    output.settings.put("separator", separator);
    return output;
  }
}
