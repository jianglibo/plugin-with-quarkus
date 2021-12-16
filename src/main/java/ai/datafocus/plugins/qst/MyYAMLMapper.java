package ai.datafocus.plugins.qst;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import lombok.Getter;

@Singleton
public class MyYAMLMapper {
  // https://docs.jboss.org/cdi/spec/2.0/cdi-spec.html#bean_defining_annotations

  @Getter
  private final ObjectMapper mapper;

  public MyYAMLMapper() {
    this.mapper = new ObjectMapper(new YAMLFactory());
  }

}
