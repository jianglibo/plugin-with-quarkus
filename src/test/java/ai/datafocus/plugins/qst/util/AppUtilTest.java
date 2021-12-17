package ai.datafocus.plugins.qst.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class AppUtilTest {

  @TempDir Path anotherTempDir;

  @Test
  void tFindNewestVersionJar() throws IOException {
    Files.writeString(
        anotherTempDir.resolve("plugin-with-quarkus-0.1.3-SNAPSHOT-runner.jar"), "hello");
    Files.writeString(
        anotherTempDir.resolve("plugin-with-quarkus-0.1.2-SNAPSHOT-runner.jar"), "hello");
    Files.writeString(anotherTempDir.resolve("plugin-with-quarkus-0.1.2-runner.jar"), "hello");
    Path file = AppUtil.findNewestVersionJar(anotherTempDir);

    assertThat(file).endsWith(Path.of("plugin-with-quarkus-0.1.3-SNAPSHOT-runner.jar"));
  }
}
