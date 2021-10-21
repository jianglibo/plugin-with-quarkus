package ai.datafocus.plugins.qst;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import ai.datafocus.plugins.qst.dto.DcsPlugin;
import ai.datafocus.plugins.qst.dto.DcsPluginInstance;
import ai.datafocus.plugins.qst.dto.DcsPluginInstance.InstanceVars;
import ai.datafocus.plugins.qst.rest.TimeStep;
import ai.datafocus.plugins.qst.util.JasyptUtil;
import lombok.Data;
import lombok.Getter;

/** 通常你可以在这里解析DCS_TO_PLUGIN的意义。 */
@Singleton
public class MyConfig {

  @ConfigProperty(name = "DCS_TO_PLUGIN")
  String toPluginStr;

  TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};

  @Getter private DcsPlugin dcsPlugin;

  @Getter private DcsPluginInstance dcsPluginInstance;
  @Getter private TimeStep timeStep;

  @Getter
  private String separator;

  @Inject ObjectMapper mapper;

  @Data
  public static class QueueConfig {
    private String uri;
    private String exchange_name;
    private String queue_name;
    private String routing_key;
  }

  /**
   * 主要是解析toplugin参数
   * <pre>
   * {
   *   "separator": "08877f24-464a-4867-b58c-6b500349dae1",
   *   "plugin_instance": {...},
   *   "state": {...}
   * }
   * </pre>
   * 
   * @throws JsonMappingException
   * @throws JsonProcessingException
   */
  @PostConstruct
  void postContruct() throws JsonMappingException, JsonProcessingException {
    // test toPluginStr. 可能是加密的字串，需要解密
    String toPluginJsonStr = toPluginStr;
    if (!toPluginStr.trim().startsWith("{")) { // it's not a json string
      toPluginJsonStr = JasyptUtil.decrypt(toPluginStr);
    }
    // 不直接变成ToPlugin？因为还有变数
    Map<String, Object> toPlugin = mapper.readValue(toPluginJsonStr, typeRef);

    this.separator = (String) toPlugin.get("separator");

    if (separator == null) {
      throw new RuntimeException("separator is a must. may use uuid.");
    }

    if (toPlugin.get("plugin_instance") instanceof String) {
      this.dcsPluginInstance =
          mapper.readValue((String) toPlugin.get("plugin_instance"), DcsPluginInstance.class);
    } else { // from config file. it's easy to write json.
      String serializedState = mapper.writeValueAsString(toPlugin.get("plugin_instance"));
      this.dcsPluginInstance = mapper.readValue(serializedState, DcsPluginInstance.class);
    }

      dcsPluginInstance.setInstanceVars(
          mapper.readValue(
              mapper.writeValueAsString(dcsPluginInstance.getVars()), InstanceVars.class));

    if (toPlugin.get("state") == null) {
      setInitTimeStep();
    } else if (toPlugin.get("state") instanceof String) {
      timeStep = mapper.readValue((String) toPlugin.get("state"), TimeStep.class);
      if (timeStep.getModified_begin() == null) {
        setInitTimeStep();
      }
    } else { // 通常情况下，控制器传过来的时候已经将state从字符串变成了json
      String serializedState = mapper.writeValueAsString(toPlugin.get("state"));
      timeStep = mapper.readValue(serializedState, TimeStep.class);
      if (timeStep.getModified_begin() == null) {
        setInitTimeStep();
      }
    }
  }

  private void setInitTimeStep() {
    this.timeStep =
        TimeStep.initCreate(
            dcsPluginInstance.getInstanceVars().getModified_begin(),
            dcsPluginInstance.getInstanceVars().getPage_size(),
            dcsPluginInstance.getInstanceVars().getStep_days());
  }
}
