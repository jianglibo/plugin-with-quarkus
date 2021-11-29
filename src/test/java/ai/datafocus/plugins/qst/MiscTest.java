package ai.datafocus.plugins.qst;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Base64;
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

  // @Test
  // void tJasypt() throws IOException {
  //   String stringToEncode = UtilFort.loadFromResource("to-plugin.json");
  //   String lastString = JasyptUtil.encrypt(stringToEncode);
  //   assertThat(lastString).startsWith("ENC(");
  //   System.out.println(lastString);
  // }
}
