package ai.datafocus.plugins.qst;

import com.fasterxml.jackson.core.JsonProcessingException;

import picocli.CommandLine.Command;

@Command(name = "collect", mixinStandardHelpOptions = true)
public class CollectDataCommand implements Runnable {

    // @Parameters(paramLabel = "<name>", defaultValue = "picocli",
    //     description = "Your name.")
    // String name;

    // @ConfigProperty(name = "RABBITMQ_ADDRESS_UNKNOWN", defaultValue="!") 
    // String rabbitUrl;

    private final CollectLoopingService collectLoopingService;

    public CollectDataCommand(CollectLoopingService collectLoopingService) { 
        this.collectLoopingService = collectLoopingService;
    }

    @Override
    public void run() {
        try {
            collectLoopingService.startLooping();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
