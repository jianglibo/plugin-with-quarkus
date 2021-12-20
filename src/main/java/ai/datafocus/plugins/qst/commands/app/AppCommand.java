package ai.datafocus.plugins.qst.commands.app;

import ai.datafocus.plugins.qst.commands.app.AppConfigMapping.AppDescription;
import ai.datafocus.plugins.qst.dto.ShellExecuteResult;
import ai.datafocus.plugins.qst.util.AppUtil;
import ai.datafocus.plugins.qst.util.SshCommand;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;
import javax.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "app", mixinStandardHelpOptions = true)
public class AppCommand {

  @Inject AppConfigMapping appConfigMapping;

  SshCommand sshCommand = new SshCommand();

  @Command(name = "deploy")
  String deplpy(
      @CommandLine.Option(
              names = "--name",
              required = true,
              description = "the name of the application to deploy")
          AppName name,
      @CommandLine.Option(
              names = "--print-output",
              required = true,
              description = "print output of the ssh command.")
          boolean printOutput)
      throws InterruptedException, IOException {
    AppDescription application = appConfigMapping.findApp(name);
    stopIfNotZero(AppUtil.gitPull(application), printOutput);
    stopIfNotZero(AppUtil.gradleBuild(application), printOutput);
    Path jar =
        AppUtil.findNewestVersionJar(application.projectRoot().resolve(application.jarsInDir()));
    stopIfNotZero(
        sshCommand.scpTo(
            application.sshConnectStr(),
            jar,
            "/data01/datafocus/node/df80/pv/datafocus/src/bin/dcs/dcs-plugin-server.jar"),
        printOutput);
    stopIfNotZero(
        sshCommand.exec(
            application.sshConnectStr(),
            "chown",
            "datafocus:datafocus",
            "/data01/datafocus/node/df80/pv/datafocus/src/bin/dcs/",
            "-R"),
        printOutput);
    // kubectl --namespace=dcs-plugin delete -f /data01/datafocus/node/df80/yaml/dcs/deploy/
    stopIfNotZero(
        sshCommand.exec(
            application.sshConnectStr(),
            "kubectl",
            "--namespace=dcs-plugin",
            "delete",
            "-f",
            "/data01/datafocus/node/df80/yaml/dcs/deploy/"),
        printOutput);
    stopIfNotZero(
        sshCommand.exec(
            application.sshConnectStr(),
            "kubectl",
            "--namespace=dcs-plugin",
            "create",
            "-f",
            "/data01/datafocus/node/df80/yaml/dcs/deploy/"),
        printOutput);

    return null;
  }

  private void stopIfNotZero(ShellExecuteResult result, boolean printOutput) {
    if (printOutput) {
      if (result.getOutputs() != null) {
        for (String line : result.getOutputs()) {
          System.out.println(line);
        }
      }
    }
    if (result.getExitCode() != 0) {
      throw new RuntimeException(result.getOutputs().stream().collect(Collectors.joining("\n")));
    }
  }
}
