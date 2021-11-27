package ai.datafocus.plugins.qst;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.datafocus.plugins.qst.dto.DcsPluginInstance;
import ai.datafocus.plugins.qst.dto.MockOutOfPlugin;
import ai.datafocus.plugins.qst.dto.MockRow;
import ai.datafocus.plugins.qst.dto.MockState;
import ai.datafocus.plugins.qst.dto.OutputType;
import ai.datafocus.plugins.qst.dto.ToPluginStdio;
import ai.datafocus.plugins.qst.util.AppUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.Getter;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * This command need to know the instanceId.
 */
@Command(name = "redis", mixinStandardHelpOptions = true)
public class RedisDataCommand implements Runnable {

  @Inject ObjectMapper mapper;
  @Inject AppUtil appUtil;
  @Inject MyConfig myconfig;

  @CommandLine.Option(names = "--per-page", description = "number of the items per page. default ${DEFAULT-VALUE}")
  int perPage = 100;

  @CommandLine.Option(names = "--pages", description = "the total page number. default: ${DEFAULT-VALUE}")
  int pages = 10;

  @CommandLine.Option(names = "--name-length", description = "the length of the name field. default: ${DEFAULT-VALUE}")
  int nameLength = 50;

  @Getter
  String host;
  @Getter
  int port;
  @Getter
  String username;
  @Getter
  String password;
  @Getter
  String channel;
  @Getter
  String key;

  String separator;
  DcsPluginInstance dcsPluginInstance;

  @Override
  public void run() {
    RedisURI uri = RedisURI.create(host, port);
    if (!username.isBlank()) {
      uri.setUsername(username);
    }
    uri.setPassword(password.toCharArray());

    RedisClient redisClient = RedisClient.create(uri);
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    RedisCommands<String, String> syncCommands = connection.sync();
    String message = "message";
        long n =
        syncCommands.publish(
            channel, message == null ? username + " published a message." : message);
  }


  public void pareseRedisMock(int per_page, int name_length)
      throws JsonMappingException, JsonProcessingException {

    ToPluginStdio toPlugin = mapper.readValue(myconfig.toPluginStr.orElse("{}"), ToPluginStdio.class);

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

  private List<MockRow> genRandomRow(MockState state) {
    List<MockRow> rows = new ArrayList<>();
    int max = state.getCurrent_id() + state.getPer_page();
    for (int i = state.getCurrent_id(); i < max; i++) {
      MockRow row =
          MockRow.builder()
              .id(i)
              .age(ThreadLocalRandom.current().nextInt(120))
              .name(randomString(nameLength))
              .build();
      rows.add(row);
    }
    return rows;
  }

  private String randomString(int targetStringLength) {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    Random random = new Random();

    return random
        .ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
