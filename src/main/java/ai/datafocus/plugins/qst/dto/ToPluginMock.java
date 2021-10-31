package ai.datafocus.plugins.qst.dto;

import lombok.Data;

@Data
public class ToPluginMock {
  private OutputType output_to;
  private DcsPluginInstance plugin_instance;
  private MockState state;
}
