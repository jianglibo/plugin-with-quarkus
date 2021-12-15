package ai.datafocus.plugins.qst.commands.KuduCommand;

import ai.datafocus.plugins.qst.MyConfig;
import ai.datafocus.plugins.qst.util.AppUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import org.apache.kudu.client.KuduClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import picocli.CommandLine.Command;

/** This command doesn't need to know the instanceId. */
@Command(
    name = "kudu",
    mixinStandardHelpOptions = true,
    subcommands = {KuduCreateTable.class})
public class KuduMainCommand {

  @Inject ObjectMapper mapper;
  @Inject AppUtil appUtil;
  @Inject MyConfig myconfig;

  @Inject
  @ConfigProperty(name = "kudu.master")
  private String kuduMaster;

  public KuduClient kuduClient() {
    KuduClient kudu = new KuduClient.KuduClientBuilder(kuduMaster).build();
    return kudu;
  }
}
