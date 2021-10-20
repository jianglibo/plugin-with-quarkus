package ai.datafocus.plugins.qst.dto;

import java.util.Map;

import lombok.Data;

@Data
public class DcsPlugin {
  private Integer id;
  private String name;
  private Integer author_id;
  private String table_description;
  private String secret;
  private Map<String, Object> queue_config;
  private Map<String, Object> vars;

  private QueueConfig queueConfig;

  @Data
  public static class QueueConfig {
    private String uri;
    private String exchange_name;
    private String queue_name;
    private String routing_key;
  }
}
