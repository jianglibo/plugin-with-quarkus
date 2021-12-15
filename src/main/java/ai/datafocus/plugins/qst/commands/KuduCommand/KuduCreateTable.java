package ai.datafocus.plugins.qst.commands.KuduCommand;

import ai.datafocus.plugins.qst.MyConfig;
import ai.datafocus.plugins.qst.util.AppUtil;
import ai.datafocus.plugins.qst.util.KuduClientUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import org.apache.kudu.client.KuduException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

@Command(name = "create-table", mixinStandardHelpOptions = true)
public class KuduCreateTable implements Runnable {

  @Inject ObjectMapper mapper;
  @Inject AppUtil appUtil;
  @Inject MyConfig myconfig;

  @ParentCommand KuduMainCommand kuduMain;

  @CommandLine.Option(
      names = "--table-name",
      required = true,
      description = "The kudu table name to be created.")
  private String tableName;

  @Override
  public void run() {
    try {
      KuduClientUtil.createTable(kuduMain.kuduClient(), tableName);
    } catch (KuduException e) {
      e.printStackTrace();
    } finally {

    }
  }
}
