package ai.datafocus.plugins.qst;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import ai.datafocus.plugins.qst.commands.DisplayConfigCommand;
import ai.datafocus.plugins.qst.commands.RedisDataCommand;
import ai.datafocus.plugins.qst.commands.StdioDataCommand;
import ai.datafocus.plugins.qst.commands.app.AppCommand;
import ai.datafocus.plugins.qst.commands.hasura.HasuraCommand;
import ai.datafocus.plugins.qst.commands.kuducommand.KuduTableCommand;
import ai.datafocus.plugins.qst.commands.pluginserver.PluginServerCommand;
import io.quarkus.logging.Log;
import io.quarkus.picocli.runtime.annotations.TopCommand;
import lombok.Getter;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.ScopeType;

@TopCommand
@CommandLine.Command(
    mixinStandardHelpOptions = true,
    subcommands = {
      StdioDataCommand.class,
      RedisDataCommand.class,
      KuduTableCommand.class,
      DisplayConfigCommand.class,
      HasuraCommand.class,
      AppCommand.class,
      PluginServerCommand.class
    })
public class EntryCommand {

  private boolean[] verbosity;

  @Getter @Inject MyConfig myconfig;

  @Inject
  @ConfigProperty(name = "hasura.fixture-dir")
  Path fixtureDir;

  public int verbosity() {
    return verbosity == null ? 0 : verbosity.length;
  }

  @Option(
      names = {"-v", "--verbose"},
      scope = ScopeType.INHERIT,
      description = {
        "Specify multiple -v options to increase verbosity.",
        "For example, `-v -v -v` or `-vvv`"
      })
  public void setVerbose(boolean[] verbosity) {
    this.verbosity = verbosity;
  }

  @PostConstruct
  void after() {
    Path workdingDir = Path.of(".").toAbsolutePath();
    Path envFile = workdingDir.resolve(".env");
    if (!Files.exists(envFile)) {
      Log.warnf(
          "There is no '.env' file in the working directory (%s), maybe you should create one or"
              + " switch to the folder there is a .env file.",
          workdingDir);
    }

    if (!Files.exists(fixtureDir)) {
      Log.warnf(
          "The hasura graphql fixtures folder does't exists, you should change the setting item"
              + " 'hasura.fixture-dir' in the '.env' file. Let it points to the fixture directory."
              + " Usually it is located in the project 'fdatafocus' in the folder"
              + " 'dcs-plugin-server/fixtrures'",
          workdingDir);
    }
  }
}
