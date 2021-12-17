package ai.datafocus.plugins.qst.util;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Singular;

public class CommonCommand extends ShellExecCommand {

  @Builder
  private static CommonCommand of(
      String exec,
      boolean escalatePrivilege,
      boolean debug,
      Path workingDirectory,
      Consumer<String> consumer,
      @Singular Map<String, String> envPairs,
      @Singular List<String> commandLines) {
    assert exec != null : "exec of command is mandontory";
    CommonCommand instance = new CommonCommand();
    instance.init(exec, escalatePrivilege, workingDirectory, consumer, debug, envPairs);
    instance
        .getCommandLines()
        .addAll(
            commandLines.stream()
                .filter(l -> l != null && !l.isEmpty())
                .collect(Collectors.toList()));
    instance.prepared();
    return instance;
  }
}
