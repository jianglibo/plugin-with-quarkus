package ai.datafocus.plugins.qst.rest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import ai.datafocus.plugins.qst.UtilFort;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TimeStepTest {

  @Inject ObjectMapper mapper;

  @Test
  void tBuilder() throws JsonProcessingException {
    TimeStep timeStep = TimeStep.initCreate("2021-10-18 17:55:55", 50, 7);
    String js = mapper.writeValueAsString(timeStep);
    UtilFort.out(js);
    TimeStep timeStep1 = mapper.readValue(js, TimeStep.class);
    assertThat(timeStep).isEqualTo(timeStep1);
  }
}
