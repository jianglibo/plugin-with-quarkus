package ai.datafocus.plugins.qst;

import ai.datafocus.plugins.qst.commands.KuduCommand.KuduMainCommand;
import ai.datafocus.plugins.qst.commands.RedisDataCommand;
import ai.datafocus.plugins.qst.commands.StdioDataCommand;
import io.quarkus.picocli.runtime.annotations.TopCommand;
import javax.inject.Inject;
import lombok.Getter;
import picocli.CommandLine;

@TopCommand
@CommandLine.Command(
    mixinStandardHelpOptions = true,
    subcommands = {StdioDataCommand.class, RedisDataCommand.class, KuduMainCommand.class})
public class EntryCommand {

  @Getter @Inject MyConfig myconfig;
}
