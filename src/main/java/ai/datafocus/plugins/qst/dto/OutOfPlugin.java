package ai.datafocus.plugins.qst.dto;

import java.util.List;

import ai.datafocus.plugins.qst.rest.TimeStep;
import ai.datafocus.plugins.qst.resultdatatype.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutOfPlugin {
  private TimeStep state;
  private List<Order> data;
}
