package ai.datafocus.plugins.qst.rabbit;

import ai.datafocus.plugins.AppProperties;
import ai.datafocus.plugins.qst.dto.MockRow;
import ai.datafocus.plugins.qst.util.AppUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.Builder;
import lombok.Data;

@ApplicationScoped
public class MessangeSender {
  private Connection connection;

  @Inject private AppProperties appProperties;
  @Inject ObjectMapper jsonMapper;

  public Channel getChannel() throws IOException, TimeoutException {
    synchronized (this) {
      if (connection == null) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(appProperties.rabbit().host());
        factory.setPort(appProperties.rabbit().port());
        connection = factory.newConnection();
        //  Channel channel = connection.createChannel()) {
        //   channel.queueDeclare(appProperties.rabbit().queueName(), false, false, false, null);
        // }
      }
      return connection.createChannel();
    }
  }

  public void sendMessage(
      int instanceId, String exchange, String routingKey, int count, int startId, int nameLength)
      throws IOException, TimeoutException {
    Channel channel = getChannel();
    for (int i = 0; i < count; i++) {
      MockRow row = AppUtil.genRandomRow(i + startId, nameLength);
      channel.basicPublish(
          exchange,
          routingKey,
          null,
          jsonMapper.writeValueAsBytes(
              MessageWithInstanceId.builder().id(instanceId).row(row).build()));
    }
  }

  @Data
  @Builder
  public static class MessageWithInstanceId {
    private int id;
    private MockRow row;
  }
}
