package ai.datafocus.plugins.qst.commands.app;

import ai.datafocus.plugins.qst.commands.app.AppConfigMapping.AppDescription;
import ai.datafocus.plugins.qst.commands.app.AppConfigMapping.ProbeEndpoint;
import ai.datafocus.plugins.qst.dto.ShellExecuteResult;
import ai.datafocus.plugins.qst.util.AppUtil;
import ai.datafocus.plugins.qst.util.PureHttp;
import ai.datafocus.plugins.qst.util.PureHttp.RequestResult;
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
              names = "--namespace",
              defaultValue = "datafocus",
              description = "the namespace deploys to.  ${DEFAULT-VALUE} ")
          String namespace,
      @CommandLine.Option(names = "--restart-only", description = "restart the pod only.")
          boolean restartOnly,
      @CommandLine.Option(names = "--silent", description = "don't print output of the command.")
          boolean silent)
      throws InterruptedException, IOException {
    AppDescription application = appConfigMapping.findApp(name);
    if (!restartOnly) {
      stopIfNotZero(AppUtil.gitPull(application), silent);
      stopIfNotZero(AppUtil.gradleBuild(application), silent);
      Path jar =
          AppUtil.findNewestVersionJar(application.projectRoot().resolve(application.jarsInDir()));
      stopIfNotZero(
          sshCommand.scpTo(
              application.sshConnectStr(),
              jar,
              "/data01/datafocus/node/df80/pv/datafocus/src/bin/dcs/dcs-plugin-server.jar",
              silent),
          silent);
      stopIfNotZero(
          sshCommand.exec(
              application.sshConnectStr(),
              "chown",
              "datafocus:datafocus",
              "/data01/datafocus/node/df80/pv/datafocus/src/bin/dcs/",
              "-R"),
          silent);
    }
    // kubectl --namespace=dcs-plugin delete -f /data01/datafocus/node/df80/yaml/dcs/deploy/
    stopIfNotZero(
        sshCommand.exec(
            application.sshConnectStr(),
            "kubectl",
            "--namespace=" + namespace,
            "delete",
            "-f",
            "/data01/datafocus/node/df80/yaml/dcs/deploy/"),
        silent);
    stopIfNotZero(
        sshCommand.exec(
            application.sshConnectStr(),
            "kubectl",
            "--namespace=" + namespace,
            "create",
            "-f",
            "/data01/datafocus/node/df80/yaml/dcs/deploy/"),
        silent);
    return "success";
  }

  @Command(name = "check-running")
  String checkRunning(
      @CommandLine.Option(
              names = "--name",
              required = true,
              description = "the name of the application to deploy")
          AppName name,
      @CommandLine.Option(
              names = "--print-body",
              description = "print check result even if the result is as expected")
          boolean printBody)
      throws InterruptedException, IOException {
    AppDescription application = appConfigMapping.findApp(name);
    if (application.probeEndpoint().isPresent()) {
      ProbeEndpoint pe = application.probeEndpoint().get();
      try {
        RequestResult result = PureHttp.request(pe.url(), pe.method(), pe.body(), pe.headers());
        result.printMessage(pe.expectedHttpStatus(), printBody);
      } catch (Exception e) {
        System.out.println(String.format("check running failed: %s", e.getMessage()));
        e.printStackTrace();
      }
    } else {
      System.out.println("application didn't config check-running.");
    }
    return "success";
  }

  private void stopIfNotZero(ShellExecuteResult result, boolean silent) {
    if (!silent) {
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
