package ai.datafocus.plugins.qst.util;

import static org.assertj.core.api.Assertions.assertThat;

import ai.datafocus.plugins.qst.dto.ShellExecuteResult;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class GradleCommandTest {

  @Test
  void tBuild() throws IOException, InterruptedException {
    GradleCommand test = new GradleCommand();
    ShellExecuteResult exitCode =
        test.build(Path.of(".").resolve("../fdatacollect"), "build", "-x", "test");
    assertThat(exitCode.getExitCode()).isEqualTo(0);
  }
}
