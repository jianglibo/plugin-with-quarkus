package ai.datafocus.plugins.qst;

import java.util.HashMap;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.core.type.TypeReference;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import ai.datafocus.plugins.qst.dto.DcsPlugin;
import ai.datafocus.plugins.qst.dto.DcsPluginInstance;
import ai.datafocus.plugins.qst.dto.MockState;
import lombok.Getter;

@ApplicationScoped
public class MyConfig {

  /** from environment variable. Plugin server will prepare for you. */
  @Inject
  @ConfigProperty(name = "DCS_TO_PLUGIN")
  @Getter
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
}
