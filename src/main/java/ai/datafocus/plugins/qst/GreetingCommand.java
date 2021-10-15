package ai.datafocus.plugins.qst;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class GreetingCommand implements Runnable {

    @Parameters(paramLabel = "<name>", defaultValue = "picocli",
        description = "Your name.")
    String name;

    @ConfigProperty(name = "RABBITMQ_ADDRESS_PRODUCT", defaultValue="!") 
    String rabbitUrl;

    private final GreetingService greetingService;

    public GreetingCommand(GreetingService greetingService) { 
        this.greetingService = greetingService;
    }

    @Override
    public void run() {
        greetingService.sayHello(name, rabbitUrl);
    }

}
