package ai.datafocus.plugins.qst.dto;

import ai.datafocus.plugins.qst.rest.TimeStep;
import lombok.Data;

@Data
public class ToPlugin {
  private OutputType output_to;
  private DcsPluginInstance plugin_instance;
  private TimeStep state;
}
