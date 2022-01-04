package ai.datafocus.plugins;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "app")
public interface AppProperties {

  Rabbit rabbit();

  interface Rabbit {
    String exchange();
    String routingKey();

    String queueName();

    int port();

    String host();
  }
}
