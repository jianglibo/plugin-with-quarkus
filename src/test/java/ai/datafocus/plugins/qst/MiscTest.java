package ai.datafocus.plugins.qst;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

// import ai.datafocus.plugins.qst.util.JasyptUtil;

public class MiscTest {

  @Test
  void encode() throws IOException {
    String stringToEncode = UtilFort.loadFromResource("to-plugin.json");
    String encodedString = Base64.getEncoder().encodeToString(stringToEncode.getBytes());
    String lastString = String.format("ENC(%s)", encodedString);
    assertThat(lastString).startsWith("ENC(");
    System.out.println(lastString);
  }

  @Test
  void sortFile() throws IOException {

    List<String> fns =
        Arrays.asList(
            "plugin-with-quarkus-0.1.3-SNAPSHOT-runner.jar",
            "plugin-with-quarkus-0.1.2-SNAPSHOT-runner.jar");
    Collections.sort(fns);
    Collections.reverse(fns);

    assertThat(fns).first().asString().contains("0.1.3");
  }
}
