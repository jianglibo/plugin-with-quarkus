package ai.datafocus.plugins.qst.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MockRow {
  private int id;
  private String name;
  private int age;
}
