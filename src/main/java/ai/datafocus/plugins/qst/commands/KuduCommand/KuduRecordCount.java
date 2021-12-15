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

@Command(name = "record-count", mixinStandardHelpOptions = true)
public class KuduRecordCount implements Runnable {

  @Inject ObjectMapper mapper;
  @Inject AppUtil appUtil;
  @Inject MyConfig myconfig;

  @ParentCommand KuduMainCommand kuduMain;

  @CommandLine.Option(
      names = "--table-name",
      required = true,
      description = "The kudu table name in which the record number to be count.")
  private String tableName;

  @Override
  public void run() {
    try {
      int count = KuduClientUtil.countResult(kuduMain.kuduClient(), tableName);
      System.out.println(String.format("the record count of the table %s is %s", tableName, count));
    } catch (KuduException e) {
      e.printStackTrace();
    } finally {

    }
  }
}
