package ai.datafocus.plugins.qst.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradleCommand {

  public int build(Path projectRoot, String... args) throws IOException, InterruptedException {
    List<String> lines = new ArrayList<>();
    lines.addAll(Arrays.asList(args));
    String gcmd = OsUtil.isWindows ? ".\\gradlew.bat" : "./gradlew";
    return CommonCommand.builder()
        .exec(gcmd)
        .workingDirectory(projectRoot.toAbsolutePath().normalize())
        .commandLines(lines)
        .build()
        .play();
  }
}
