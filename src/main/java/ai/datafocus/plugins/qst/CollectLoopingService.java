package ai.datafocus.plugins.qst;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;

import ai.datafocus.plugins.qst.dto.DcsPluginInstance.InstanceVars;
import ai.datafocus.plugins.qst.dto.OutOfPlugin;
import ai.datafocus.plugins.qst.rest.JuShuiTanResource;
import ai.datafocus.plugins.qst.rest.TimeStep;
import ai.datafocus.plugins.qst.resultdatatype.Order;
import ai.datafocus.plugins.qst.resultdatatype.OrderQueryResult;
import ai.datafocus.plugins.qst.util.AppUtil;
import io.quarkus.logging.Log;

@Singleton
public class CollectLoopingService {

  @Inject Logger log;

  @Inject RabbitmqService rabbitmqService;
  @Inject MyConfig myconfig;
  @Inject ObjectMapper mapper;

  @Inject AppUtil appUtil;

  @Inject JuShuiTanResource jushuitanResource;

  public void startLooping() throws JsonProcessingException {
    InstanceVars instanceVars = myconfig.getDcsPluginInstance().getInstanceVars();
    TimeStep timeStep =
        TimeStep.initCreate(
            instanceVars.getModified_begin(),
            instanceVars.getPage_size(),
            instanceVars.getStep_days());
    int loop_times = 0;
    boolean loop_time_limited = instanceVars.getLoop_times() > 0;
    while (timeStep != null) {
      OrderQueryResult result = jushuitanResource.getOrders(timeStep.toOrderQueryBody());
      for (Order order : result.getOrders()) {
        try {
          rabbitmqService.publish(order);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      // Log.info(result);
      timeStep = timeStep.nextPage(result.getOrders().size());
      if (loop_time_limited) {
        loop_times += 1;
        if (loop_times >= instanceVars.getLoop_times()) {
          Log.info("loop_time_limited: " + loop_times + " done");
          break;
        }
      }
    }
  }

  /**
   * 如果返回的state是null，那么将不再issue新的Fire-and-forget的任务。
   * 
   * @throws JsonMappingException
   * @throws JsonProcessingException
   */
  public void once() throws JsonMappingException, JsonProcessingException {
    OrderQueryResult result =
        jushuitanResource.getOrders(myconfig.getTimeStep().toOrderQueryBody());
    OutOfPlugin outOfPlugin =
        OutOfPlugin.builder()
            .state(myconfig.getTimeStep().nextPage(result.getOrders().size()))
            .data(result.getOrders())
            .build();
    appUtil.printOutData(outOfPlugin);
  }
}
