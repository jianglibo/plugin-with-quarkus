package ai.datafocus.plugins.qst;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.PreDestroy;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import ai.datafocus.plugins.qst.resultdatatype.Order;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;

@Singleton
public class RabbitmqService {

  @Inject MyConfig myconfig;

  @Inject ObjectMapper mapper;

  private Connection conn;
  private Channel channel;

  void startup(@Observes StartupEvent event)
      throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException, IOException,
          TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(myconfig.getDcsPlugin().getQueueConfig().getUri());
    this.conn = factory.newConnection();
    this.channel = conn.createChannel();
    Log.debug(channel);
    // channel.exchangeDeclare(exchangeName, "direct", true);
    // channel.queueDeclare(queueName, true, false, false, null);
    // channel.queueBind(queueName, exchangeName, routingKey);
  }

  @PreDestroy
  void preDestroy() {
    try {
      if (channel != null) {
        channel.close();
      }
      if (conn != null) {
        conn.close();
      }
    } catch (IOException | TimeoutException e) {
      e.printStackTrace();
    }
  }

  void publish(Order order) throws IOException {
    Map<String, Object> headers = new HashMap<String, Object>();
    headers.put("latitude", 51.5252949);
    headers.put("longitude", -0.0905493);
    byte[] messageBodyBytes = mapper.writeValueAsString(order).getBytes();
    channel.basicPublish(
        myconfig.getDcsPlugin().getQueueConfig().getExchange_name(),
        myconfig.getDcsPlugin().getQueueConfig().getRouting_key(),
        new AMQP.BasicProperties.Builder()
            .headers(headers)
            .contentType(MediaType.APPLICATION_JSON)
            .deliveryMode(2)
            .priority(1)
            // .userId("bob")
            .build(),
        messageBodyBytes);
  }

  // void receive() throws IOException {
  //   boolean autoAck = false;
  //   GetResponse response = channel.basicGet(queueName, autoAck);
  //   if (response == null) {
  //     // No message retrieved.
  //   } else {
  //     AMQP.BasicProperties props = response.getProps();
  //     byte[] body = response.getBody();
  //     long deliveryTag = response.getEnvelope().getDeliveryTag();
  //     // ...
  //   }
  // }

}
