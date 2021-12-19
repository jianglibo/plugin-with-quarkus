package ai.datafocus.plugins.qst.commands;

import picocli.CommandLine.Command;

@Command(name = "keep", mixinStandardHelpOptions = true)
public class LongRunCommand implements Runnable {

  @Override
  public void run() {
    try {
      Thread.sleep(1000 * 60 * 5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
