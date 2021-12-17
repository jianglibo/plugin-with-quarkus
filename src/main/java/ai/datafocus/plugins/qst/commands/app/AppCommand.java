package ai.datafocus.plugins.qst.commands.app;

import ai.datafocus.plugins.qst.commands.app.AppConfigMapping.AppDescription;
import ai.datafocus.plugins.qst.util.AppUtil;
import java.io.IOException;
import javax.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "app", mixinStandardHelpOptions = true)
public class AppCommand {

  @Inject AppConfigMapping appConfigMapping;

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
