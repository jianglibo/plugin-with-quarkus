package ai.datafocus.plugins.qst;

import ai.datafocus.plugins.qst.dto.DcsPluginInstance;
import ai.datafocus.plugins.qst.dto.MockOutOfPlugin;
import ai.datafocus.plugins.qst.dto.MockRow;
import ai.datafocus.plugins.qst.dto.MockState;
import ai.datafocus.plugins.qst.dto.OutputType;
import ai.datafocus.plugins.qst.dto.ToPluginStdio;
import ai.datafocus.plugins.qst.util.AppUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/** This command doesn't need to know the instanceId. */
@Command(name = "stdio", mixinStandardHelpOptions = true)
public class StdioDataCommand implements Runnable {

  @Inject ObjectMapper mapper;
  @Inject AppUtil appUtil;
  @Inject MyConfig myconfig;

  @CommandLine.Option(
      names = "--per-page",
      description = "number of the items per page. default ${DEFAULT-VALUE}")
  int perPage = 100;

  @CommandLine.Option(
      names = "--pages",
      description = "the total page number. default: ${DEFAULT-VALUE}")
  int pages = 10;

  @CommandLine.Option(
      names = "--name-length",
      description = "the length of the name field. default: ${DEFAULT-VALUE}")
  int nameLength = 50;

  private MockState state;

  private String separator;

  private DcsPluginInstance dcsPluginInstance;

  @Override
  public void run() {
    try {
      pareseStdioMock(perPage, nameLength);
      List<MockRow> rows = new ArrayList<>();
      if (state.getCurrent_page() <= pages) {
        rows = genRandomRow(state);
      }
      state.increamentCurrentPage();
      MockOutOfPlugin outOfPlugin = MockOutOfPlugin.builder().state(state).data(rows).build();

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
      MockRow row = AppUtil.genRandomRow(i, nameLength);
      rows.add(row);
    }
    return rows;
  }

  public void pareseStdioMock(int per_page, int name_length)
      throws JsonMappingException, JsonProcessingException {

    ToPluginStdio toPlugin =
        mapper.readValue(myconfig.toPluginStr.orElse("{}"), ToPluginStdio.class);

    OutputType output_to = toPlugin.getOutput_to();

    this.separator = (String) output_to.getSettings().get("separator");

    if (separator == null) {
      throw new RuntimeException("separator is a must. may use uuid.");
    }

    this.dcsPluginInstance = toPlugin.getPlugin_instance();

    if (toPlugin.getState() == null || toPlugin.getState().getPer_page() == 0) {
      this.state =
          MockState.builder()
              .current_id(1)
              .current_page(1)
              .per_page(per_page)
              .name_length(name_length)
              .build();
    } else {
      this.state = toPlugin.getState();
    }
  }
}
