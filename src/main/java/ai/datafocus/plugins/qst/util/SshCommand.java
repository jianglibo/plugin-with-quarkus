package ai.datafocus.plugins.qst.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SshCommand {

  private boolean debug;

  public String exec(String hostString, String... args) throws InterruptedException, IOException {
    Stream.Builder<String> sb = Stream.builder();
    List<String> lines = new ArrayList<>();
    lines.add(hostString);
    lines.addAll(Arrays.asList(args));
    CommonCommand.builder()
        .exec("ssh")
        .commandLines(lines)
        .debug(debug)
        .consumer(sb)
        .build()
        .play();
    return sb.build().collect(Collectors.joining("\n"));
  }

  public String scpTo(String hostString, Path local, String remote)
      throws InterruptedException, IOException {
    Stream.Builder<String> sb = Stream.builder();
    CommonCommand.builder()
        .exec("scp")
        .commandLine(local.toAbsolutePath().normalize().toString())
        .commandLine(hostString + ":" + remote)
        .debug(debug)
        .consumer(sb)
        .build()
        .play();
    return sb.build().collect(Collectors.joining("\n"));
  }
}
