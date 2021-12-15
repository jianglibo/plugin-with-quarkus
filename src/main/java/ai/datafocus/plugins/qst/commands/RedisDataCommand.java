package ai.datafocus.plugins.qst.commands;

import ai.datafocus.plugins.qst.MyConfig;
import ai.datafocus.plugins.qst.dto.DcsPluginInstance;
import ai.datafocus.plugins.qst.dto.OutputType;
import ai.datafocus.plugins.qst.dto.ToPluginStdio;
import ai.datafocus.plugins.qst.util.AppUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.quarkus.logging.Log;
import javax.inject.Inject;
import lombok.Getter;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/** This command need to know the instanceId. */
@Command(name = "redis", mixinStandardHelpOptions = true)
public class RedisDataCommand implements Runnable {

  @Inject ObjectMapper mapper;
  @Inject AppUtil appUtil;
  @Inject MyConfig myconfig;

  @CommandLine.Option(
      names = "--howmany",
      description = "the total page number. default: ${DEFAULT-VALUE}")
  int howmany = 10;

  @CommandLine.Option(
      names = "--name-length",
      description = "the length of the name field. default: ${DEFAULT-VALUE}")
  int nameLength = 50;

  @Getter String host;
  @Getter int port;
  @Getter String username;
  @Getter String password;
  @Getter String channel;
  @Getter String key;

  String separator;
  DcsPluginInstance dcsPluginInstance;

  @Override
  public void run() {
    try {
      pareseRedisMock();
      lettuce();
    } catch (Exception e) {
      // 如果程序的IO输出中包含传递进来的separator，那么整个IO输出的内容会保存在数据库中
      // 插件的作者可以有权限查询，用于调试可能的状况。
      System.out.println(separator);
      Log.error(e);
      Log.error(e.getCause());
      try {
        redis();
      } catch (Exception e1) {
        Log.error(e1);
        Log.error(e1.getCause());
      }
    }
  }

  private void lettuce() throws JsonProcessingException {
    RedisURI uri = RedisURI.create(host, port);
    if (!username.isBlank()) {
      uri.setUsername(username);
    }
    uri.setPassword(password.toCharArray());

    RedisClient redisClient = RedisClient.create(uri);
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    RedisCommands<String, String> syncCommands = connection.sync();

    for (int i = 0; i < howmany; i++) {
      String message = mapper.writeValueAsString(AppUtil.genRandomRow(i, nameLength));
      syncCommands.publish(channel, message);
    }
  }

  public void redis() throws JsonProcessingException {
    Config config = new Config();
    String address = String.format("redis://%s:%s", host, port);
    config.useSingleServer().setAddress(address).setPassword(password);
    RedissonClient client = Redisson.create(config);
    RTopic topic = client.getTopic(channel);
    for (int i = 0; i < howmany; i++) {
      String message = mapper.writeValueAsString(AppUtil.genRandomRow(i, nameLength));
      topic.publish(message);
    }
  }

  public void pareseRedisMock() throws JsonMappingException, JsonProcessingException {

    ToPluginStdio toPlugin =
        mapper.readValue(myconfig.getToPluginStr().orElse("{}"), ToPluginStdio.class);

    OutputType output_to = toPlugin.getOutput_to();

    this.separator = (String) output_to.getSettings().get("separator");

    if (output_to.getSettings().containsKey("host")) {
      host = (String) output_to.getSettings().get("host");
      port = (Integer) output_to.getSettings().get("port");
      username = (String) output_to.getSettings().get("username");
      password = (String) output_to.getSettings().get("password");
      channel = (String) output_to.getSettings().get("channel");
      key = (String) output_to.getSettings().get("key");

    } else {
      host = "redis-master";
      port = 6379;
      username = "";
      password = "AhmooN2Ohki8Lae8Ze";
      channel = "channel:51";
      key = "state:51";
    }

    if (separator == null) {
      throw new RuntimeException("separator is a must. may use uuid.");
    }
    this.dcsPluginInstance = toPlugin.getPlugin_instance();
  }
}
