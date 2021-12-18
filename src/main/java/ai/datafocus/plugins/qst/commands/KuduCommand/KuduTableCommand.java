package ai.datafocus.plugins.qst.commands.kuducommand;

import ai.datafocus.plugins.qst.EntryCommand;
import ai.datafocus.plugins.qst.util.KuduClientUtil;
import javax.inject.Inject;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

/** This command doesn't need to know the instanceId. */
@Command(name = "kudu", mixinStandardHelpOptions = true, version = "1.0")
public class KuduTableCommand {

  @ParentCommand EntryCommand parent;

  @Inject
  @ConfigProperty(name = "kudu.master")
  private String kuduMaster;

  public KuduClient kuduClient() {
    // if (parent.verbosity() > 0) {
    //   Log.infof("kuduMaster: %s", kuduMaster);
    // }
    KuduClient kudu = new KuduClient.KuduClientBuilder(kuduMaster).build();
    return kudu;
  }

  @Command(name = "create-table")
  void createTable(
      @CommandLine.Option(
              names = "--table-name",
              required = true,
              description = "The kudu table name to be created.")
          String tableName) {
    try {
      KuduClientUtil.createTable(kuduClient(), tableName);
    } catch (KuduException e) {
      e.printStackTrace();
    } finally {

    }
  }

  @Command(name = "record-count")
  void recordCount(
      @CommandLine.Option(
              names = "--table-name",
              required = true,
              description = "The kudu table name in which the record number to be count.")
          String tableName) {
    try {
      int count = KuduClientUtil.countResult(kuduClient(), tableName);
      System.out.println(String.format("the record count of the table %s is %s", tableName, count));
    } catch (KuduException e) {
      e.printStackTrace();
    } finally {

    }
  }

  @Command(name = "delete-table")
  void deleteTable(
      @CommandLine.Option(
              names = "--table-name",
              required = true,
              description = "The kudu table name about to delete.")
          String tableName) {
    try {
      KuduClientUtil.deleteTable(kuduClient(), tableName);
      System.out.println(String.format("Table %s deleted successfully", tableName));
    } catch (KuduException e) {
      e.printStackTrace();
    } finally {

    }
  }
}
