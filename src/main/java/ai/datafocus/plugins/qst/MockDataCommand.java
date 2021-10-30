package ai.datafocus.plugins.qst;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.datafocus.plugins.qst.util.AppUtil;
import picocli.CommandLine.Command;

@Command(name = "mock", mixinStandardHelpOptions = true)
public class MockDataCommand implements Runnable {

  @Inject ObjectMapper mapper;
  @Inject AppUtil appUtil;

  @Override
  public void run() {
      appUtil.printOutData("");
  }
}
