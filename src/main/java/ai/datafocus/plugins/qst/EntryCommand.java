package ai.datafocus.plugins.qst;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

@TopCommand
@CommandLine.Command(mixinStandardHelpOptions = true, subcommands = {MockDataCommand.class, CollectDataCommand.class})
public class EntryCommand {
	
}
