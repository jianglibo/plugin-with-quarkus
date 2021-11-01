package ai.datafocus.plugins.qst;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DcsColumn {
  private String name;
  private String type;
  private boolean key;
}
