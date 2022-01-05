package ai.datafocus.plugins.qst.commands;

import ai.datafocus.plugins.AppProperties;
import ai.datafocus.plugins.qst.rabbit.MessangeSender;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "rabbit", mixinStandardHelpOptions = true)
public class RabbitCommand {

  @Inject private MessangeSender messageSender;
  @Inject private AppProperties appProperties;

  @Command(name = "send-to-queue")
  String sendToQueue(
      @CommandLine.Option(
              names = "--instance-id",
              description = "the plugin instance id.",
              required = true)
          int instanceId,
      @CommandLine.Option(
              names = "--exchange",
              description = "the exchange name to send to.",
              required = false)
          String exchange,
      @CommandLine.Option(
              names = "--routing-key",
              required = false,
              description = "the routing key.")
          String routingKey,
      @CommandLine.Option(
              names = "--start-id",
              defaultValue = "0",
              description = "the start id of the generated messages. default: ${DEFAULT-VALUE}")
          int startId,
      @CommandLine.Option(
              names = "--count",
              defaultValue = "100",
              description = "the count of the messages to send. default: ${DEFAULT-VALUE}")
          int count,
      @CommandLine.Option(
              names = "--name-length",
              defaultValue = "100",
              description = "the length of the name field. default: ${DEFAULT-VALUE}")
          int nameLength)
      throws IOException, TimeoutException {

    messageSender.sendMessage(
        instanceId,
        exchange == null ? appProperties.rabbit().exchange() : exchange,
        routingKey == null ? appProperties.rabbit().routingKey() : routingKey,
        count,
        startId,
        nameLength);
    System.out.printf("sending %s messages done.", count);
    System.out.println();
    return "success.";
  }
}
