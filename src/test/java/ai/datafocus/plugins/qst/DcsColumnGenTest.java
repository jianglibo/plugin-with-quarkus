package ai.datafocus.plugins.qst;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DcsColumnGenTest {

  @Test
  void tDcsColumnGen() throws JsonProcessingException {
    List<DcsColumn> columns = new ArrayList<DcsColumn>();
    columns.add(DcsColumn.builder().name("id").type("int").key(true).build());
    columns.add(DcsColumn.builder().name("name").type("string").key(false).build());
    columns.add(DcsColumn.builder().name("age").type("int").key(false).build());
    System.out.println(new ObjectMapper().writeValueAsString(columns));
  }
}
