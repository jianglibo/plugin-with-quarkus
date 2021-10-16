package ai.datafocus.plugins.qst.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderQueryBody {
  private int page_index;
  private int page_size;
  private String modified_begin;
  private String modified_end;
}
