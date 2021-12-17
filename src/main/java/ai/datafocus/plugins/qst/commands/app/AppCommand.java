package ai.datafocus.plugins.qst.commands.app;

import ai.datafocus.plugins.qst.commands.app.AppConfigMapping.AppDescription;
import ai.datafocus.plugins.qst.util.AppUtil;
import ai.datafocus.plugins.qst.util.SshCommand;
import java.io.IOException;
import java.nio.file.Path;
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
          AppName name)
      throws InterruptedException, IOException {
    AppDescription application = appConfigMapping.findApp(name);
    int code = AppUtil.gitPull(application);

    Path jar = AppUtil.findNewestVersionJar(application.projectRoot().resolve("build"));
    sshCommand.scpTo(
        application.sshConnectStr(),
        jar,
        "/data01/datafocus/node/df80/pv/datafocus/src/bin/dcs/dcs-plugin-server.jar");
    sshCommand.exec(
        application.sshConnectStr(),
        "chown",
        "datafocus:datafocus",
        "/data01/datafocus/node/df80/pv/datafocus/src/bin/dcs/",
        "-R");
    sshCommand.exec(
        application.sshConnectStr(),
        "kb",
        "delete",
        "-f",
        "/data01/datafocus/node/df80/yaml/dcs/deploy/");

    sshCommand.exec(
        application.sshConnectStr(),
        "kb",
        "create",
        "-f",
        "/data01/datafocus/node/df80/yaml/dcs/deploy/");

    if (code != 0) {
      System.out.println("git pull failed.");
    }
    code = AppUtil.gradleBuild(application);
    if (code != 0) {
      System.out.println("gradle build failed.");
    }
    return null;
  }
}
