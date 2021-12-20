package ai.datafocus.plugins.qst.commands.hasura;

import ai.datafocus.plugins.qst.CommonTypeReferences;
import ai.datafocus.plugins.qst.MyYAMLMapper;
import ai.datafocus.plugins.qst.dto.HasuraResponse;
import ai.datafocus.plugins.qst.service.HasuraService;
import ai.datafocus.plugins.qst.util.HasuraUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Command(
    name = "hasura",
    mixinStandardHelpOptions = true,
    subcommands = {HelpCommand.class})
public class HasuraCommand {

  @Inject @RestClient HasuraService service;

  @Inject HasuraUtil hasuraUtil;

  @Inject
  @ConfigProperty(name = "hasura.fixture-dir")
  Path fixtureDir;

  @Inject ObjectMapper jsonMapper;

  @Inject MyYAMLMapper yamlMapper;

  @Command(name = "author-create")
  HasuraResponse createAuthor(
      @CommandLine.Option(
              names = "--name",
              required = true,
              description = "The name of the author.")
          String name)
      throws IOException {
    String fromFile =
        Files.readString(fixtureDir.resolve("author-insert.yml").toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    alterValueAtIfNotNull_variables_object(jbody, name, "name");
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    dumpHasuraResponse(response);
    return response;
  }

  @Command(name = "author-delete")
  void deleteAuthor(
      @CommandLine.Option(
              names = "--name",
              required = true,
              description = "The name of the author.")
          String name)
      throws IOException {
    String fromFile =
        Files.readString(
            fixtureDir.resolve("author-delete-byname.yml").toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    alterValueAtIfNotNull_variables(jbody, name, "name");
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    dumpHasuraResponse(response);
  }

  @Command(name = "plugin-create")
  void createPlugin(
      @CommandLine.Option(
              names = "--yaml-file",
              defaultValue = "plugin-insert-demo.yml",
              description =
                  "The yaml file that describe the plugin to create. default:  ${DEFAULT-VALUE}.")
          String yamlFile,
      @CommandLine.Option(
              names = "--author-id",
              required = false,
              description =
                  "The id of the author. if you didn't provide this argument the application will"
                      + " create a random author.")
          Integer authorId,
      @CommandLine.Option(names = "--name", required = false, description = "The name to update.")
          String name,
      @CommandLine.Option(
              names = "--executable-tpl",
              required = false,
              description = "The executable_tpl to update.")
          String executableTpl,
      @CommandLine.Option(
              names = "--output-to",
              required = false,
              description = "The communication method between the plugin and the server.")
          OutputTo outputTo)
      throws IOException {
    String fromFile = Files.readString(fixtureDir.resolve(yamlFile).toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    if (authorId != null) {
      alterValueAtIfNotNull_variables_object(jbody, authorId, "author_id");
    } else {
      HasuraResponse response = createAuthor(UUID.randomUUID().toString());
      int newAuthorId = (int) response.oneResponse().get("id");
      alterValueAtIfNotNull_variables_object(jbody, newAuthorId, "author_id");
    }
    alterValueAtIfNotNull_variables_object(jbody, name, "name");
    alterValueAtIfNotNull_variables_object(jbody, executableTpl, "executable_tpl");
    alterValueAtIfNotNull_variables_object(jbody, outputTo, "output_to");
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    dumpHasuraResponse(response);
  }

  @Command(name = "plugin-update")
  void updatePlugin(
      @CommandLine.Option(
              names = "--yaml-file",
              required = false,
              description = "The the plugin update file.")
          String yamlFile,
      @CommandLine.Option(names = "--id", required = true, description = "The id of the plugin.")
          Integer id,
      @CommandLine.Option(names = "--name", required = false, description = "The name to update.")
          String name,
      @CommandLine.Option(
              names = "--executable-tpl",
              required = false,
              description = "The executable_tpl to update.")
          String executableTpl,
      @CommandLine.Option(
              names = "--output-to",
              required = false,
              description = "The communication method between the plugin and the server.")
          OutputTo outputTo,
      @CommandLine.Option(
              names = "--env",
              required = false,
              description =
                  "env pairs to update. to add by adding plus prefix +quarkus.log.level=WARN, to"
                      + " delete by adding minus prefix -quarkus.log.level=WARN")
          List<String> envs)
      throws IOException {
    String fromFile =
        Files.readString(
            fixtureDir
                .resolve(yamlFile == null ? "plugin-update.yml" : yamlFile)
                .toAbsolutePath()
                .normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    alterValueAtIfNotNull_variables(jbody, id, "id");
    alterValueAtIfNotNull_variables_object(jbody, name, "name");
    alterValueAtIfNotNull_variables_object(jbody, executableTpl, "executable_tpl");
    alterValueAtIfNotNull_variables_object(jbody, outputTo, "output_to");

    if (envs != null && envs.size() > 0) {
      Map<String, Object> originEnvs = getStringObjectMap(pluginByPk(id), "envs");
      for (String oneEnv : envs) {
        if (oneEnv.startsWith("-")) {
          String[] ss = oneEnv.substring(1).split("=", 2);
          originEnvs.remove(ss[0]);
        } else {
          if (oneEnv.startsWith("+")) {
            oneEnv = oneEnv.substring(1);
          }
          String[] ss = oneEnv.split("=", 2);
          originEnvs.put(ss[0], ss[1]);
        }
      }
      alterValueAtIfNotNull_variables_object(jbody, originEnvs, "envs");
    }

    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    dumpHasuraResponse(response);
  }

  @SuppressWarnings("unchecked")
  private Map<String, Object> getStringObjectMap(Map<String, Object> parent, String key) {
    return (Map<String, Object>) parent.get(key);
  }

  private Map<String, Object> pluginByPk(Integer id) throws IOException {
    String fromFile =
        Files.readString(fixtureDir.resolve("plugin-by-pk.yml").toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    alterValueAtIfNotNull_variables(jbody, id, "id");
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    return response.oneResponse();
  }

  @Command(name = "plugin-list")
  void listPlugin(
      @CommandLine.Option(
              names = "--offset",
              required = false,
              description = "The the start of the list")
          Integer offset,
      @CommandLine.Option(
              names = "--limit",
              required = false,
              description = "the limit of return records.")
          Integer limit,
      @CommandLine.Option(
              names = "--detail",
              required = false,
              defaultValue = "false",
              description = "display the detail of the fetched plugins.")
          boolean detail)
      throws IOException {
    String fromFile =
        Files.readString(
            fixtureDir
                .resolve(detail ? "plugin-list-detail.yml" : "plugin-list.yml")
                .toAbsolutePath()
                .normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);

    alterValueAtIfNotNull_variables(jbody, offset, "offset");
    alterValueAtIfNotNull_variables(jbody, limit, "limit");
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    dumpHasuraResponse(response);
  }

  @Command(name = "instance-list")
  void listInstance(
      @CommandLine.Option(
              names = "--offset",
              required = false,
              description = "The the start of the list")
          Integer offset,
      @CommandLine.Option(
              names = "--limit",
              required = false,
              description = "the limit of return records.")
          Integer limit,
      @CommandLine.Option(
              names = "--plugin-id",
              required = true,
              description = "the plugin id to list it's instances.")
          int pluginId)
      throws IOException {
    String fromFile =
        Files.readString(fixtureDir.resolve("instance-list.yml").toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    alterValueAtIfNotNull_variables(jbody, pluginId, "plugin_id");
    alterValueAtIfNotNull_variables(jbody, offset, "offset");
    alterValueAtIfNotNull_variables(jbody, limit, "limit");
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    dumpHasuraResponse(response);
  }

  @Command(name = "instance-create")
  void createInstance(
      @CommandLine.Option(
              names = "--yaml-file",
              defaultValue = "instance-insert-demo.yml",
              description =
                  "The yaml file that describe the instance to create. default:  ${DEFAULT-VALUE}.")
          String yamlFile,
      @CommandLine.Option(
              names = "--name",
              required = false,
              description = "The name of the instance.")
          String name,
      @CommandLine.Option(
              names = "--table-name",
              required = false,
              description = "The table_name of the instance.")
          String tableName,
      @CommandLine.Option(
              names = "--plugin-id",
              required = true,
              description =
                  "The id of the plugin. If you didn't provide a plugin-id the application will"
                      + " create a random plugin. ")
          Integer pluginId,
      @CommandLine.Option(
              names = "--cron",
              required = false,
              description = "The cron expression of the instance.")
          String cron)
      throws IOException {
    String fromFile = Files.readString(fixtureDir.resolve(yamlFile).toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    alterValueAtIfNotNull_variables_object(jbody, pluginId, "dcs_plugin_id");
    alterValueAtIfNotNull_variables_object(jbody, name, "name");
    alterValueAtIfNotNull_variables_object(jbody, tableName, "table_name");
    if (cron != null) {
      if ("null".equalsIgnoreCase(cron)) {
        hasuraUtil.alterValueAt(jbody, null, "variables", "object", "cron");
      } else {
        alterValueAtIfNotNull_variables_object(jbody, cron, "cron");
      }
    }
    String body = jsonMapper.writeValueAsString(jbody);
    dumpHasuraResponse(service.doGraphql(body));
  }

  @Command(name = "instance-touch")
  void touchInstance(
      @CommandLine.Option(names = "--id", required = true, description = "The id of the plugin.")
          Integer id)
      throws IOException {
    String fromFile =
        Files.readString(fixtureDir.resolve("instance-touch.yml").toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    alterValueAtIfNotNull_variables(jbody, id, "id");
    String body = jsonMapper.writeValueAsString(jbody);
    dumpHasuraResponse(service.doGraphql(body));
  }

  private void dumpHasuraResponse(HasuraResponse response) {
    if (response.hasError()) {
      response.dumpErrors();
    } else {
      response.dumpData();
    }
  }

  @Command(name = "instance-update")
  void updateInstance(
      @CommandLine.Option(
              names = "--yaml-file",
              required = false,
              description = "The the instance update file.")
          String yamlFile,
      @CommandLine.Option(names = "--id", required = true, description = "The id of the instance.")
          Integer id,
      @CommandLine.Option(names = "--name", required = false, description = "The name to update.")
          String name,
      @CommandLine.Option(
              names = "--table-name",
              required = false,
              description = "The table_name to update.")
          String tableName,
      @CommandLine.Option(names = "--cron", required = false, description = "The cron to update.")
          String cron)
      throws IOException {
    String fromFile =
        Files.readString(
            fixtureDir
                .resolve(yamlFile == null ? "instance-update.yml" : yamlFile)
                .toAbsolutePath()
                .normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    alterValueAtIfNotNull_variables(jbody, id, "id");
    alterValueAtIfNotNull_variables_object(jbody, name, "name");
    alterValueAtIfNotNull_variables_object(jbody, tableName, "table_name");
    if (cron != null) {
      if ("null".equalsIgnoreCase(cron)) {
        hasuraUtil.alterValueAt(jbody, null, "variables", "object", "cron");
      } else {
        alterValueAtIfNotNull_variables_object(jbody, cron, "cron");
      }
    }
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    dumpHasuraResponse(response);
  }

  @Command(name = "error-list")
  void listError(
      @CommandLine.Option(
              names = "--offset",
              required = false,
              description = "The the start of the list")
          Integer offset,
      @CommandLine.Option(
              names = "--limit",
              required = false,
              description = "the limit of return records.")
          Integer limit)
      throws IOException {
    String fromFile =
        Files.readString(fixtureDir.resolve("error-list.yaml").toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);

    alterValueAtIfNotNull_variables(jbody, offset, "offset");
    alterValueAtIfNotNull_variables(jbody, limit, "limit");

    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    if (response.hasError()) {
      response.dumpErrors();
    } else {
      String sep =
          "\n"
              + "---------------------------------------------ERROR-ITEM-----------------------------------------------\n";
      for (Map<String, Object> item : response.listResponse()) {
        System.out.println(sep);
        System.out.println(item);
      }
      System.out.println(sep);
    }
  }

  private void alterValueAtIfNotNull_variables_object(
      Map<String, Object> jbody, Object newValue, String fieldName) {
    if (newValue != null) {
      hasuraUtil.alterValueAt(jbody, newValue, "variables", "object", fieldName);
    }
  }

  private void alterValueAtIfNotNull_variables(
      Map<String, Object> jbody, Object newValue, String fieldName) {
    if (newValue != null) {
      hasuraUtil.alterValueAt(jbody, newValue, "variables", fieldName);
    }
  }

  public static enum OutputTo {
    STDIO,
    REDIS
  }
}
