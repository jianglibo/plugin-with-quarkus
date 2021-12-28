package ai.datafocus.plugins.qst.commands.app;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.microprofile.config.spi.Converter;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithConverter;

@ConfigMapping(prefix = "apps")
public interface AppConfigMapping {
  Map<String, AppDescription> deploys();

  default AppDescription findApp(AppName appName) {
    return deploys().get(appName.name());
  }

  interface AppDescription {
    String sshConnectStr();

    Path projectRoot();

    String gitUrl();

    String jarsInDir();

    Optional<ProbeEndpoint> probeEndpoint();

    @WithConverter(CommaSeparatorConverter.class)
    List<String> buildCommand();
  }

  interface ProbeEndpoint {
    String url();

    String method();

    String expectedHttpStatus();

    Optional<String> body();

    Map<String, String> headers();
  }

  public static class CommaSeparatorConverter implements Converter<List<String>> {
    @Override
    public List<String> convert(final String value) {
      String stripped = value.trim();
      String[] parts;
      if (stripped.contains(",,,")) {
        parts = stripped.split(",,,");
      } else if (stripped.contains(",,")) {
        parts = stripped.split(",,,");
      } else {
        parts = stripped.split(",");
      }

      return Stream.of(parts).map(String::trim).collect(Collectors.toList());
    }
  }
}
