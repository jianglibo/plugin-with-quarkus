package ai.datafocus.plugins.qst;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.StartupEvent;

@Dependent
public class RabbitmqService {

    @ConfigProperty(name = "RABBITMQ_ADDRESS_PRODUCT", defaultValue="!") 
    String rabbitUrl;

      void startup(@Observes StartupEvent event) { 
	      ConnectionFactory factory = new ConnectionFactory();
factory.setUri("amqp://userName:password@hostName:portNumber/virtualHost");
Connection conn = factory.newConnection();
	      
}

	
}
