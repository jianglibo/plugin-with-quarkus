package ai.datafocus.plugins.qst.commands.hasura;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import ai.datafocus.plugins.qst.CommonTypeReferences;
import ai.datafocus.plugins.qst.MyYAMLMapper;
import ai.datafocus.plugins.qst.dto.HasuraResponse;
import ai.datafocus.plugins.qst.service.HasuraService;
import ai.datafocus.plugins.qst.util.HasuraUtil;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Command(name = "hasura", mixinStandardHelpOptions = true, subcommands = {HelpCommand.class})
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
    hasuraUtil.alterValueAt(jbody, name, "variables", "object", "name");
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    if (response.hasError()) {
      response.dumpErrors();
    } else {
      response.dumpData();
    }
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
    hasuraUtil.alterValueAt(jbody, name, "variables", "name");
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    if (response.hasError()) {
      response.dumpErrors();
    } else {
      response.dumpData();
    }
  }

  @Command(name = "plugin-create")
  void createPlugin(
      @CommandLine.Option(
              names = "--yaml-file",
              defaultValue = "plugin-insert-demo.yml",
              description = "The yaml file that describe the plugin to create. default:  ${DEFAULT-VALUE}.")
          String yamlFile,
      @CommandLine.Option(
              names = "--author-id",
              required = false,
              description = "The id of the author.")
          Integer authorId)
      throws IOException {
    String fromFile = Files.readString(fixtureDir.resolve(yamlFile).toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    if (authorId != null) {
      hasuraUtil.alterValueAt(jbody, authorId, "variables", "object", "author_id");
    } else {
      HasuraResponse response = createAuthor(UUID.randomUUID().toString());
      int newAuthorId = (int) response.oneResponse().get("id");
      hasuraUtil.alterValueAt(jbody, newAuthorId, "variables", "object", "author_id");
    }
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    if (response.hasError()) {
      response.dumpErrors();
    } else {
      response.dumpData();
    }
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
          String executableTpl)
      throws IOException {
    String fromFile =
        Files.readString(
            fixtureDir
                .resolve(yamlFile == null ? "plugin-update.yml" : yamlFile)
                .toAbsolutePath()
                .normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    hasuraUtil.alterValueAt(jbody, id, "variables", "id");
    if (name != null) {
      hasuraUtil.alterValueAt(jbody, name, "variables", "object", "name");
    }
    if (executableTpl != null) {
      hasuraUtil.alterValueAt(jbody, executableTpl, "variables", "object", "executable_tpl");
    }
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    if (response.hasError()) {
      response.dumpErrors();
    } else {
      response.dumpData();
    }
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
              description = "the limit of return records.")
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
    if (offset != null) {
      hasuraUtil.alterValueAt(jbody, offset, "variables", "offset");
    }
    if (limit != null) {
      hasuraUtil.alterValueAt(jbody, limit, "variables", "limit");
    }
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    if (response.hasError()) {
      response.dumpErrors();
    } else {
      response.dumpData();
    }
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
              description = "the plugin id to list instances.")
          int pluginId)
      throws IOException {
    String fromFile =
        Files.readString(fixtureDir.resolve("instance-list.yml").toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);

    hasuraUtil.alterValueAt(jbody, pluginId, "variables", "plugin_id");
    if (offset != null) {
      hasuraUtil.alterValueAt(jbody, offset, "variables", "offset");
    }
    if (limit != null) {
      hasuraUtil.alterValueAt(jbody, limit, "variables", "limit");
    }
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    if (response.hasError()) {
      response.dumpErrors();
    } else {
      response.dumpData();
    }
  }

  @Command(name = "instance-create")
  void createInstance(
      @CommandLine.Option(
              names = "--yaml-file",
              required = true,
              description = "The name of the author.")
          String yamlFile,
      @CommandLine.Option(
              names = "--plugin-id",
              required = false,
              description = "The id of the plugin.")
          Integer pluginId)
      throws IOException {
    String fromFile = Files.readString(fixtureDir.resolve(yamlFile).toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    Consumer<Integer> alt =
        (pid) -> hasuraUtil.alterValueAt(jbody, pid, "variables", "object", "dcs_plugin_id");
    ;
    if (pluginId != null) {
      alt.accept(pluginId);
    } else {
      HasuraResponse response = createAuthor(UUID.randomUUID().toString());
      int newAuthorId = (int) response.oneResponse().get("id");
      alt.accept(newAuthorId);
    }
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
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
              description = "The the plugin update file.")
          String yamlFile,
      @CommandLine.Option(names = "--id", required = true, description = "The id of the plugin.")
          Integer id,
      @CommandLine.Option(names = "--name", required = false, description = "The name to update.")
          String name,
      @CommandLine.Option(
              names = "--table-name",
              required = false,
              description = "The table_name to update.")
          String tableName)
      throws IOException {
    String fromFile =
        Files.readString(
            fixtureDir
                .resolve(yamlFile == null ? "instance-update.yml" : yamlFile)
                .toAbsolutePath()
                .normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    hasuraUtil.alterValueAt(jbody, id, "variables", "id");
    if (name != null) {
      hasuraUtil.alterValueAt(jbody, name, "variables", "object", "name");
    }
    if (tableName != null) {
      hasuraUtil.alterValueAt(jbody, tableName, "variables", "object", "table_name");
    }
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    if (response.hasError()) {
      response.dumpErrors();
    } else {
      response.dumpData();
    }
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
          Integer limit
          )
      throws IOException {
    String fromFile =
        Files.readString(fixtureDir.resolve("error-list.yaml").toAbsolutePath().normalize());
    Map<String, Object> jbody =
        yamlMapper.getMapper().readValue(fromFile, CommonTypeReferences.string2object);
    if (offset != null) {
      hasuraUtil.alterValueAt(jbody, offset, "variables", "offset");
    }
    if (limit != null) {
      hasuraUtil.alterValueAt(jbody, limit, "variables", "limit");
    }
    String body = jsonMapper.writeValueAsString(jbody);
    HasuraResponse response = service.doGraphql(body);
    if (response.hasError()) {
      response.dumpErrors();
    } else {
      response.dumpData();
    }
  }

}
