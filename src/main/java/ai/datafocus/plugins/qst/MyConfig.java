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
import lombok.Getter;

@Singleton
public class MyConfig {

  /** from environment variable. Plugin server will prepare for you. */
  @ConfigProperty(name = "DCS_TO_PLUGIN")
  Optional<String> toPluginStr;

  TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
  };

  @Getter
  private DcsPlugin dcsPlugin;

  @Getter
  private DcsPluginInstance dcsPluginInstance;

  @Getter
  private MockState mockState;

  @Getter
  private String separator;

  @Inject
  ObjectMapper mapper;



}
