package ai.datafocus.plugins.qst.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;

public class JasyptUtilTest {

  @Test
  void tPattern() {
    Matcher matcher = JasyptUtil.pattern.matcher("ENC(abc)");
    assertThat(matcher.matches()).isTrue();
    assertThat(matcher.group(1)).isEqualTo("abc");
  }
}
