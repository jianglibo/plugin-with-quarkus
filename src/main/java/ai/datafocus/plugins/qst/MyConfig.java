package ai.datafocus.plugins.qst;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import ai.datafocus.plugins.qst.dto.DcsPlugin;
import ai.datafocus.plugins.qst.dto.DcsPluginInstance;
import lombok.Data;
import lombok.Getter;

@Dependent
public class MyConfig {

  @ConfigProperty(name = "DCS_PLUGIN")
  String dcsPluginEnv;

  @ConfigProperty(name = "DCS_PLUGIN_INSTANCE")
  String dcsPluginInstanceEnv;

  @Getter private DcsPlugin dcsPlugin;

  @Getter private DcsPluginInstance dcsPluginInstance;

  @Inject ObjectMapper mapper;

  @Data
  public static class QueueConfig {
    private String uri;
    private String exchange_name;
    private String queue_name;
    private String routing_key;
  }

  @PostConstruct
  void postContruct() {
    try {
      this.dcsPlugin = mapper.readValue(dcsPluginEnv, DcsPlugin.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException("DCS_PLUGIN is not a valid json string.");
    }

    try {
      this.dcsPluginInstance = mapper.readValue(dcsPluginInstanceEnv, DcsPluginInstance.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException("DCS_QUEUE_CONFIG is not a valid json string.");
    }
  }
}
