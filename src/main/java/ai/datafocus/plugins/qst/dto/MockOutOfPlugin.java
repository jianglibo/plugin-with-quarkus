package ai.datafocus.plugins.qst.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MockOutOfPlugin {
  private MockState state;
  private List<MockRow> data;
}
