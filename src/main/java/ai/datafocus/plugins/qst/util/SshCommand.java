package ai.datafocus.plugins.qst.util;

import ai.datafocus.plugins.qst.dto.ShellExecuteResult;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SshCommand {

  private boolean debug;

  public ShellExecuteResult exec(String hostString, String... args)
      throws InterruptedException, IOException {
    List<String> lines = new ArrayList<>();
    lines.add(hostString);
    lines.addAll(Arrays.asList(args));
    return CommonCommand.builder().exec("ssh").commandLines(lines).build().play();
  }

  public ShellExecuteResult scpTo(String hostString, Path local, String remote)
      throws InterruptedException, IOException {
    return CommonCommand.builder()
        .exec("scp")
        .commandLine(local.toAbsolutePath().normalize().toString())
        .commandLine(hostString + ":" + remote)
        .debug(debug)
        .build()
        .play();
  }
}
