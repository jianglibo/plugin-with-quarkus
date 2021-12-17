package ai.datafocus.plugins.qst.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

// @EnabledIfEnvironmentVariable(named = "TEST_FOR_PLAY", matches = "yes")
public class SshCommandTest {

  @Test
  void tLs() throws InterruptedException, IOException {
    SshCommand sshCommand = new SshCommand();
    String s = sshCommand.exec("root@builder.resp.me", "ls", "-lha", "~");
    assertThat(s).contains(".bashrc");
  }

  @Test
  void tScp() throws InterruptedException, IOException {
    SshCommand sshCommand = new SshCommand();
    sshCommand.scpTo("root@builder.resp.me", Path.of(".env.tpl"), "~/");
    String s = sshCommand.exec("root@builder.resp.me", "ls", "-lha", "~/.env.tpl");
    assertThat(s).contains(".env.tpl");
  }
}
