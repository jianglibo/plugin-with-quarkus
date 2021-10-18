package ai.datafocus.plugins.qst;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine.Command;

@Command(name = "collect", mixinStandardHelpOptions = true)
public class CollectDataCommand implements Runnable {

  @Inject ObjectMapper mapper;

  private final CollectLoopingService collectLoopingService;

  public CollectDataCommand(CollectLoopingService collectLoopingService) {
    this.collectLoopingService = collectLoopingService;
  }

  @Override
  public void run() {
    try {
      collectLoopingService.once();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
