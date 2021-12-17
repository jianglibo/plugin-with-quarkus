package ai.datafocus.plugins.qst.util;

import ai.datafocus.plugins.qst.commands.app.AppConfigMapping.AppDescription;
import ai.datafocus.plugins.qst.dto.MockRow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppUtil {

  @Inject ObjectMapper jsonMapper;

  public void printOutDataString(String data, String separator) {
    System.out.println();
    System.out.println(separator);
    System.out.println(data);
    System.out.println(separator);
    System.out.println();
  }

  public void printOutDataJson(Object data, String separator) throws JsonProcessingException {
    printOutDataString(
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(data), separator);
  }

  public static String randomString(int targetStringLength) {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    Random random = new Random();

    return random
        .ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  public static MockRow genRandomRow(int id, int nameLength) {
    return MockRow.builder()
        .id(id)
        .age(ThreadLocalRandom.current().nextInt(120))
        .name(AppUtil.randomString(nameLength))
        .build();
  }

  public static void prn(String tpl, Object o) {
    System.out.println(String.format(tpl, o));
  }

  public static int gitPull(AppDescription description) throws InterruptedException, IOException {
    return CommonCommand.builder()
        .exec("git")
        .workingDirectory(description.projectRoot().toAbsolutePath().normalize())
        .commandLine("pull")
        .build()
        .play();
  }

  public static int gradleBuild(AppDescription description)
      throws InterruptedException, IOException {
    Path cur = description.projectRoot().toAbsolutePath().normalize();
    String cmd = cur.resolve(description.buildCommand().get(0)).toAbsolutePath().toString();
    List<String> lines = description.buildCommand().subList(1, description.buildCommand().size());
    return CommonCommand.builder()
        .exec(cmd)
        .workingDirectory(cur)
        .commandLines(lines)
        .build()
        .play();
  }

  public static Path findNewestVersionJar(Path dir) throws IOException {
    List<Path> jars =
        Files.list(dir)
            .filter(p -> p.getFileName().toString().toLowerCase().endsWith(".jar"))
            .collect(Collectors.toList());
    Collections.sort(jars);
    Collections.reverse(jars);
    if (jars.size() == 0) {
      throw new RuntimeException("no jars found in the: " + dir);
    }
    return jars.get(0);
  }
}
