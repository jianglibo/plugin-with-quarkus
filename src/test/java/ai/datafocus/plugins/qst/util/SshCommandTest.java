package ai.datafocus.plugins.qst.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import ai.datafocus.plugins.qst.dto.ShellExecuteResult;

@EnabledIfEnvironmentVariable(named = "TEST_FOR_PLAY", matches = "yes")
public class SshCommandTest {

  @Test
  void tLs() throws InterruptedException, IOException {
    SshCommand sshCommand = new SshCommand();
    ShellExecuteResult s = sshCommand.exec("root@builder.resp.me", "ls", "-lha", "~");
    assertThat(s.getOutputs()).contains(".bashrc");
  }

  @Test
  void tScp() throws InterruptedException, IOException {
    SshCommand sshCommand = new SshCommand();
    sshCommand.scpTo("root@builder.resp.me", Path.of(".env.tpl"), "~/");
    ShellExecuteResult s = sshCommand.exec("root@builder.resp.me", "ls", "-lha", "~/.env.tpl");
    assertThat(s.getOutputs()).contains(".env.tpl");
  }
}
