package ai.datafocus.plugins.qst.commands.app;

import io.smallrye.config.ConfigMapping;
import java.nio.file.Path;
import java.util.List;

@ConfigMapping(prefix = "apps")
public interface AppConfigMapping {
  List<AppDescription> all();

  default AppDescription findApp(AppName appName) {
    return all().stream().filter(a -> a.appName() == appName).findAny().get();
  }

  interface AppDescription {
    String sshConnectStr();

    AppName appName();

    Path projectRoot();

    String gitUrl();

    List<String> buildCommand();
  }
}
