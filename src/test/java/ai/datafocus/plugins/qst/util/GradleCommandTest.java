package ai.datafocus.plugins.qst.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class GradleCommandTest {

  @Test
  void tBuild() throws IOException, InterruptedException {
    GradleCommand test = new GradleCommand();
    int exitCode = test.build(Path.of(".").resolve("../fdatacollect"), "build", "-x", "test");
    assertThat(exitCode).isEqualTo(0);
  }
}
