package ai.datafocus.plugins.qst;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

@TopCommand
@CommandLine.Command(
    mixinStandardHelpOptions = true,
    subcommands = {StdioDataCommand.class, RedisDataCommand.class})
public class EntryCommand {}
