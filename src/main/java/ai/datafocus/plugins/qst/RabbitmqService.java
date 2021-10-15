package ai.datafocus.plugins.qst;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.StartupEvent;

@Dependent
public class RabbitmqService {

  @ConfigProperty(name = "DCS_RABBITMQ_ADDRESS")
  String rabbitUrl;

  @ConfigProperty(name = "RABBITMQ_EXCHANGE_NAME")
  String exchangeName;

  @ConfigProperty(name = "RABBITMQ_QUEUE_NAME")
  String queueName;

  @ConfigProperty(name = "RABBITMQ_ROUTING_KEY")
  String routingKey;

  private Connection conn;
  private Channel channel;

  void startup(@Observes StartupEvent event)
      throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException, IOException,
          TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(rabbitUrl);
    setConn(factory.newConnection());
    channel = conn.createChannel();
    channel.exchangeDeclare(exchangeName, "direct", true);
    channel.queueDeclare(queueName, true, false, false, null);
    channel.queueBind(queueName, exchangeName, routingKey);
  }

  @PreDestroy
  void preDestroy() {
    try {
      channel.close();
      conn.close();
    } catch (IOException | TimeoutException e) {
      e.printStackTrace();
    }
  }

  void publish() throws IOException {
    Map<String, Object> headers = new HashMap<String, Object>();
    headers.put("latitude", 51.5252949);
    headers.put("longitude", -0.0905493);

    byte[] messageBodyBytes = "Hello, world!".getBytes();

    channel.basicPublish(
        exchangeName,
        routingKey,
        new AMQP.BasicProperties.Builder()
            .headers(headers)
            .contentType("text/plain")
            .deliveryMode(2)
            .priority(1)
            .userId("bob")
            .build(),
        messageBodyBytes);
  }

  void receive() throws IOException {
    boolean autoAck = false;
    GetResponse response = channel.basicGet(queueName, autoAck);
    if (response == null) {
      // No message retrieved.
    } else {
      AMQP.BasicProperties props = response.getProps();
      byte[] body = response.getBody();
      long deliveryTag = response.getEnvelope().getDeliveryTag();
      // ...
    }
  }

  /** @return the conn */
  public Connection getConn() {
    return conn;
  }

  /** @param conn the conn to set */
  public void setConn(Connection conn) {
    this.conn = conn;
  }
}
