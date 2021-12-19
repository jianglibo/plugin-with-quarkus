package ai.datafocus.plugins.qst.commands.app;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import io.smallrye.config.ConfigMapping;

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

    List<String> buildCommand();
  }
}
