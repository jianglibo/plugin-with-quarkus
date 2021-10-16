package ai.datafocus.plugins.qst;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.config.ConfigProvider;

import lombok.Data;
import lombok.Getter;

@Dependent
public class MyConfig {

  //   @ConfigProperty(name = "PLUGIN_INSTANCE_VARS", defaultValue = "!")
  //   String pluginInstanceVars;

  @Inject ObjectMapper mapper;

  @Getter
  private JstPartnerData instanceConfig;

  @Getter
  private QueueConfig queueConfig;

  @Data
  public static class JstPartnerData {
    private String partnerid;
    private String partnerkey;
    private String token;
    private String modified_begin;
    private int page_size;
    private int step_days;
    private int loop_times;
  }

  @Data
  public static class QueueConfig {
    private String uri;
    private String exchange_name;
    private String queue_name;
    private String routing_key;
  }

  @PostConstruct
  void postContruct() {
    Optional<String> vars =
        ConfigProvider.getConfig().getOptionalValue("PLUGIN_INSTANCE_VARS", String.class);
    if (vars.isEmpty()) {
      throw new RuntimeException("No PLUGIN_INSTANCE_VARS enviroment variables was set.");
    }
    try {
      this.instanceConfig = mapper.readValue(vars.get(), JstPartnerData.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException("PLUGIN_INSTANCE_VARS is not a valid json string.");
    }

    Optional<String> queueConfig =
        ConfigProvider.getConfig().getOptionalValue("DCS_QUEUE_CONFIG", String.class);
    if (queueConfig.isEmpty()) {
      throw new RuntimeException("No DCS_QUEUE_CONFIG enviroment variables was set.");
    }
    try {
      this.queueConfig = mapper.readValue(queueConfig.get(), QueueConfig.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException("DCS_QUEUE_CONFIG is not a valid json string.");
    }
  }
}
