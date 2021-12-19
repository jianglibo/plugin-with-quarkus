package ai.datafocus.plugins.qst.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShellExecuteResult {
  private int exitCode;
  private List<String> outputs;
}
