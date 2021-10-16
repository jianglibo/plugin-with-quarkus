package ai.datafocus.plugins.qst.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class DcsPlugin {
  private Integer id;
  private String name;
  private Integer author_id;
  private String table_description;
  private String secret;
  private String queue_config;
  private String vars;

  private QueueConfig queueConfig;

  public void pareseQueueConfig(ObjectMapper mapper)
      throws JsonMappingException, JsonProcessingException {
    this.queueConfig = mapper.readValue(queue_config, QueueConfig.class);
  }

  @Data
  public static class QueueConfig {
    private String uri;
    private String exchange_name;
    private String queue_name;
    private String routing_key;
  }
}
