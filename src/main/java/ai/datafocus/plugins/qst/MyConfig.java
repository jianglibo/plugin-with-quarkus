package ai.datafocus.plugins.qst;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import ai.datafocus.plugins.qst.dto.DcsPlugin;
import ai.datafocus.plugins.qst.dto.DcsPluginInstance;
import ai.datafocus.plugins.qst.dto.MockState;
import ai.datafocus.plugins.qst.dto.OutputType;
import ai.datafocus.plugins.qst.dto.ToPlugin;
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
  @Getter private MockState mockState;

  @Getter private String separator;

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
   *
   * <pre>
   * {
   *   "output_to": {"type": "STDIO", "settings": {"separator": "08877f24-464a-4867-b58c-6b500349dae1"}},
   *   "plugin_instance": {...},
   *   "state": {...}
   * }
   * </pre>
   *
   * @throws JsonMappingException
   * @throws JsonProcessingException
   */
  public MyConfig parese() throws JsonMappingException, JsonProcessingException {
    // test toPluginStr. 可能是加密的字串，需要解密
    String toPluginJsonStr = toPluginStr;
    if (!toPluginStr.trim().startsWith("{")) { // it's not a json string
      toPluginJsonStr = JasyptUtil.decrypt(toPluginStr);
    }
    ToPlugin toPlugin = mapper.readValue(toPluginJsonStr, ToPlugin.class);

    OutputType output_to = toPlugin.getOutput_to();

    this.separator = (String) output_to.getSettings().get("separator");

    if (separator == null) {
      throw new RuntimeException("separator is a must. may use uuid.");
    }

    this.dcsPluginInstance = toPlugin.getPlugin_instance();

    if (toPlugin.getState() == null) {
      setInitTimeStep();
    } else { // 通常情况下，控制器传过来的时候已经将state从字符串变成了json
      timeStep = toPlugin.getState();
      if (timeStep.getModified_begin() == null) {
        setInitTimeStep();
      }
    }
    return this;
  }

  public MyConfig pareseMock() throws JsonMappingException, JsonProcessingException {
    // test toPluginStr. 可能是加密的字串，需要解密
    String toPluginJsonStr = toPluginStr;
    if (!toPluginStr.trim().startsWith("{")) { // it's not a json string
      toPluginJsonStr = JasyptUtil.decrypt(toPluginStr);
    }
    ToPlugin toPlugin = mapper.readValue(toPluginJsonStr, ToPlugin.class);

    OutputType output_to = toPlugin.getOutput_to();

    this.separator = (String) output_to.getSettings().get("separator");

    if (separator == null) {
      throw new RuntimeException("separator is a must. may use uuid.");
    }

    this.dcsPluginInstance = toPlugin.getPlugin_instance();

    if (toPlugin.getState() == null) {
      setInitTimeStep();
    } else { // 通常情况下，控制器传过来的时候已经将state从字符串变成了json
      timeStep = toPlugin.getState();
      if (timeStep.getModified_begin() == null) {
        setInitTimeStep();
      }
    }
    return this;
  }

  private void setInitTimeStep() {
    this.timeStep =
        TimeStep.initCreate(
            dcsPluginInstance.getVars().getModified_begin(),
            dcsPluginInstance.getVars().getPage_size(),
            dcsPluginInstance.getVars().getStep_days());
  }
}
