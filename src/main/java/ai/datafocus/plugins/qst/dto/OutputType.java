package ai.datafocus.plugins.qst.dto;

import java.util.Map;

import lombok.Data;

@Data
public class OutputType {
  private String output_type;
  private Map<String, Object> settings;
}
