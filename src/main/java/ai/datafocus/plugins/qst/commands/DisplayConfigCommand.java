package ai.datafocus.plugins.qst.commands;

import io.quarkus.logging.Log;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import picocli.CommandLine.Command;

@Command(name = "config", mixinStandardHelpOptions = true)
public class DisplayConfigCommand {

  @Inject
  @ConfigProperty(name = "kudu.master")
  String kuduMaster;

  @Command
  void kudu() {
    Log.infof("kuduMaster: %s", kuduMaster);
  }
}
