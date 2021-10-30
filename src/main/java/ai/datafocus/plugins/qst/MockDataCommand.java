package ai.datafocus.plugins.qst;

import java.util.ArrayList;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.datafocus.plugins.qst.dto.MockOutOfPlugin;
import ai.datafocus.plugins.qst.util.AppUtil;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "mock", mixinStandardHelpOptions = true)
public class MockDataCommand implements Runnable {

  @Inject ObjectMapper mapper;
  @Inject AppUtil appUtil;
  @Inject MyConfig myconfig;

  @CommandLine.Option(names = "--per-page", description = "number of the items per page.")
  int perPage = 100;

  @CommandLine.Option(names = "--pages", description = "the total page number.")
  int pages = 10;

  @Override
  public void run() {
    MockOutOfPlugin outOfPlugin =
        MockOutOfPlugin.builder().state(myconfig.getMockState()).data(new ArrayList<>()).build();
    try {
      appUtil.printOutDataJson(outOfPlugin);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
