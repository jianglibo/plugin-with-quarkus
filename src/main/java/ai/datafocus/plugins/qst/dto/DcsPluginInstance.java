package ai.datafocus.plugins.qst.dto;


import java.util.Map;

import lombok.Data;

@Data
public class DcsPluginInstance {

  private Integer id;
  private String user_id;
  private Integer dcs_plugin_id;
  private Map<String, Object> vars;
  private String state;
  private String created_at;
  private String updated_at;
  private String cron;

  private InstanceVars instanceVars;


  @Data
  public static class InstanceVars {
    private String partnerid;
    private String partnerkey;
    private String token;
    private String modified_begin;
    private int page_size;
    private int step_days;
    private int loop_times;
    private DcsPlugin plugin;
  }
}
