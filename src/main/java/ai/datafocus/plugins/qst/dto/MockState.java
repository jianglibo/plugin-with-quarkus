package ai.datafocus.plugins.qst.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@RegisterForReflection
public class MockState {
  private int current_id;
  private int current_page;
  private int per_page;
  private int name_length;

  /**
   * current_page is 1 based. The Order is important here.
   * @param current_page
   * @param per_page
   */
  public MockState(int current_id, int current_page, int per_page,  int name_length) {
    this.current_page = current_page < 1 ? 1 : current_page;
    this.per_page = per_page;
    this.current_id = current_id;
    this.name_length = name_length;
  }

  public MockState increamentCurrentPage() {
    this.current_page++;
    this.current_id += per_page;
    return this;
  }
}
