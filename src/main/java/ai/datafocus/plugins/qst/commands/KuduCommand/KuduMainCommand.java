package ai.datafocus.plugins.qst.commands.KuduCommand;

import ai.datafocus.plugins.qst.EntryCommand;
import io.quarkus.logging.Log;
import javax.inject.Inject;
import org.apache.kudu.client.KuduClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

/** This command doesn't need to know the instanceId. */
@Command(
    name = "kudu",
    mixinStandardHelpOptions = true,
    subcommands = {KuduCreateTable.class, KuduRecordCount.class})
public class KuduMainCommand {

  @ParentCommand EntryCommand parent;

  @Inject
  @ConfigProperty(name = "kudu.master")
  private String kuduMaster;

  public KuduClient kuduClient() {
    if (parent.verbosity() > 0) {
      Log.infof("kuduMaster: %s", kuduMaster);
    }
    KuduClient kudu = new KuduClient.KuduClientBuilder(kuduMaster).build();
    return kudu;
  }
}
