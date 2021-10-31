package ai.datafocus.plugins.qst;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.datafocus.plugins.qst.dto.MockOutOfPlugin;
import ai.datafocus.plugins.qst.dto.MockRow;
import ai.datafocus.plugins.qst.dto.MockState;
import ai.datafocus.plugins.qst.util.AppUtil;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "mock", mixinStandardHelpOptions = true)
public class MockDataCommand implements Runnable {

  @Inject ObjectMapper mapper;
  @Inject AppUtil appUtil;
  @Inject MyConfig myconfig;

  @CommandLine.Option(names = "--per-page", description = "number of the items per page.")
  int perPage = 100;

  @CommandLine.Option(names = "--pages", description = "the total page number.")
  int pages = 10;

  @CommandLine.Option(names = "--name-length", description = "the length of the name field.")
  int nameLength = 50;

  @Override
  public void run() {
    try {
      MockState state = myconfig.pareseMock(perPage, nameLength).getMockState();

      List<MockRow> rows = new ArrayList<>();
      if (state.getCurrent_page() <= pages) {
        rows = genRandomRow(state);
      }
      state.increamentCurrentPage();
      MockOutOfPlugin outOfPlugin =
          MockOutOfPlugin.builder().state(state).data(rows).build();

      appUtil.printOutDataJson(outOfPlugin);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  private List<MockRow> genRandomRow(MockState state) {
    List<MockRow> rows = new ArrayList<>();
    int max = state.getCurrent_id() + state.getPer_page();
    for (int i = state.getCurrent_id(); i < max; i++) {
      MockRow row =
          MockRow.builder()
              .id(i)
              .age(ThreadLocalRandom.current().nextInt(120))
              .name(randomString(nameLength))
              .build();
      rows.add(row);
    }
    return rows;
  }

  private String randomString(int targetStringLength) {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    Random random = new Random();

    return random
        .ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
