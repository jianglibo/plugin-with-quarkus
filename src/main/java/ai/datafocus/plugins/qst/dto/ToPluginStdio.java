package ai.datafocus.plugins.qst.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class ToPluginStdio {
  private OutputType output_to;
  private DcsPluginInstance plugin_instance;
  private MockState state;
}
