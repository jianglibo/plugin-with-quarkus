package ai.datafocus.plugins.qst;

import ai.datafocus.plugins.qst.commands.DisplayConfigCommand;
import ai.datafocus.plugins.qst.commands.KuduCommand.KuduTableCommand;
import ai.datafocus.plugins.qst.commands.RedisDataCommand;
import ai.datafocus.plugins.qst.commands.StdioDataCommand;
import io.quarkus.picocli.runtime.annotations.TopCommand;
import javax.inject.Inject;
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
      DisplayConfigCommand.class
    })
public class EntryCommand {

  private boolean[] verbosity;

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

  @Getter @Inject MyConfig myconfig;
}
