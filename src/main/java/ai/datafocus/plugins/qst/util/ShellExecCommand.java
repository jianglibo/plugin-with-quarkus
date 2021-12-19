package ai.datafocus.plugins.qst.util;

import ai.datafocus.plugins.qst.dto.ShellExecuteResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public abstract class ShellExecCommand {

  private Path workingDirectory = Paths.get(".");
  private List<String> commandLines;
  private Consumer<String> consumer;
  private boolean prepareCalled;
  private boolean privilegeEscalationRequired;
  private boolean debug;
  private Map<String, String> envPairs;
  private Process process;

  protected void init(
      String exec,
      boolean escalatePrivilege,
      Path workingDirectory,
      Consumer<String> consumer,
      boolean debug,
      Map<String, String> envPairs) {
    assert exec != null : "exec of command should not be null";
    this.privilegeEscalationRequired = escalatePrivilege;
    this.workingDirectory = workingDirectory == null ? Paths.get(".") : workingDirectory;
    this.consumer = consumer;
    this.commandLines = new ArrayList<>();
    this.commandLines.add(exec);
    this.debug = debug;
    this.envPairs = envPairs;
  }

  protected void prepared() {
    prepareCalled = true;
  }

  protected void addLines(String... lines) {
    commandLines.addAll(Arrays.asList(lines));
  }

  public ShellExecuteResult play() throws InterruptedException, IOException {
    assert prepareCalled : "prepare should be called before play";
    // try {
    ProcessBuilder builder = new ProcessBuilder();
    builder.environment().putAll(envPairs);
    if (debug) {
      for (Entry<String, String> entry : builder.environment().entrySet()) {
        log.debug("env: " + entry.getKey() + "=" + entry.getValue());
      }
    }
    assert !commandLines.isEmpty() : "maybe forgot prepareCommand.";
    if (privilegeEscalationRequired && !OsUtil.isRootUser()) {
      commandLines.add(0, "sudo");
    }
    builder.command(commandLines);
    builder.redirectErrorStream(true);
    builder.directory(workingDirectory.toFile());
    process = builder.start();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    List<String> lines = new ArrayList<>();
    String line;
    while ((line = reader.readLine()) != null) {
      lines.add(line);
      if (consumer != null) {
        consumer.accept(line.trim());
      }
      log.info("line: {}", line);
    }
    int exitCode = process.waitFor();
    return ShellExecuteResult.builder().exitCode(exitCode).outputs(lines).build();
  }
}
