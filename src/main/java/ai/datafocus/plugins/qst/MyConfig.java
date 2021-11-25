package ai.datafocus.plugins.qst;

import java.util.HashMap;
import java.util.Optional;

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
import ai.datafocus.plugins.qst.dto.ToPluginStdio;
import lombok.Getter;

@Singleton
public class MyConfig {

  /** from environment variable. Plugin server will prepare for you. */
  @ConfigProperty(name = "DCS_TO_PLUGIN")
  Optional<String> toPluginStr;

  TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};

  @Getter private DcsPlugin dcsPlugin;

  @Getter private DcsPluginInstance dcsPluginInstance;

  @Getter private MockState mockState;

  @Getter private String separator;

  @Inject ObjectMapper mapper;


  public MyConfig pareseMock(int per_page, int name_length)
      throws JsonMappingException, JsonProcessingException {

    ToPluginStdio toPlugin = mapper.readValue(toPluginStr.orElse("{}"), ToPluginStdio.class);

    OutputType output_to = toPlugin.getOutput_to();

    this.separator = (String) output_to.getSettings().get("separator");

    if (separator == null) {
      throw new RuntimeException("separator is a must. may use uuid.");
    }

    this.dcsPluginInstance = toPlugin.getPlugin_instance();

    if (toPlugin.getState() == null || toPlugin.getState().getPer_page() == 0) {
      this.mockState =
          MockState.builder()
              .current_id(1)
              .current_page(1)
              .per_page(per_page)
              .name_length(name_length)
              .build();
    } else { 
      this.mockState = toPlugin.getState();
    }
    return this;
  }

}
