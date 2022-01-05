package ai.datafocus.plugins.qst.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.kudu.ColumnSchema;
import org.apache.kudu.Schema;
import org.apache.kudu.Type;
import org.apache.kudu.client.CreateTableOptions;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.apache.kudu.client.KuduScanner;
import org.apache.kudu.client.KuduTable;
import org.apache.kudu.client.ListTablesResponse;
import org.apache.kudu.client.RowResultIterator;

public class KuduClientUtil {
  /**
   *
   *
   * <pre>
   * 	"table_description": {
   * "columns": [
   * {
   * "name": "kudu_id",
   * "type": "int",
   * "key": true
   * },
   * {
   * "name": "kudu_name",
   * "type": "string",
   * "key": false
   * },
   * {
   * "name": "kudu_age",
   * "type": "int",
   * "key": false
   * }
   * ]
   * },
   * </pre>
   *
   * @return
   */
  private static List<ColumnSchema> columns() {
    List<ColumnSchema> columns = new ArrayList<>(5);
    columns.add(new ColumnSchema.ColumnSchemaBuilder("_id", Type.STRING).key(true).build());
    columns.add(new ColumnSchema.ColumnSchemaBuilder("_timestamp", Type.INT64).key(false).build());
    columns.add(new ColumnSchema.ColumnSchemaBuilder("kudu_id", Type.INT32).key(false).build());
    columns.add(
        new ColumnSchema.ColumnSchemaBuilder("kudu_name", Type.STRING).nullable(false).build());
    columns.add(new ColumnSchema.ColumnSchemaBuilder("kudu_age", Type.INT32).key(false).build());
    return columns;
  }

  public static void createTable(KuduClient client, String tableName) throws KuduException {
    createTable(client, tableName, 1, 1);
  }

  public static List<String> listTable(KuduClient client, String nameFilter) throws KuduException {
    ListTablesResponse tables = client.getTablesList(nameFilter);
    return tables.getTablesList();
  }

  private static void createTable(KuduClient client, String tableName, int replicas, int tablets)
      throws KuduException {
    try {
      Schema schema = new Schema(columns());
      CreateTableOptions builder = new CreateTableOptions();
      builder.setNumReplicas(replicas);
      builder.addHashPartitions(Arrays.asList("_id"), 10);
      client.createTable(tableName, schema, builder);
    } catch (KuduException e) {
      if (!e.getMessage().contains("already exists")) {
        throw e;
      }
    }
  }

  public static void deleteTable(KuduClient client, String tableName) throws KuduException {
    try {
      client.deleteTable(tableName);
    } catch (Exception e) {
    }
  }

  public static int countResult(KuduClient client, String tableName) throws KuduException {
    KuduTable table = client.openTable(tableName);
    List<String> projectColumns = new ArrayList<>(1);
    projectColumns.add("_id");
    int count = 0;
    KuduScanner scanner =
        client.newScannerBuilder(table).setProjectedColumnNames(projectColumns).build();
    while (scanner.hasMoreRows()) {
      RowResultIterator results = scanner.nextRows();
      while (results.hasNext()) {
        results.next();
        count++;
      }
    }
    return count;
  }
}
