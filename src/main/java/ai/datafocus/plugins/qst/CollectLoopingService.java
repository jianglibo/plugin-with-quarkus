package ai.datafocus.plugins.qst;

import java.io.IOException;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.jboss.logging.Logger;

import ai.datafocus.plugins.qst.dto.DcsPluginInstance.InstanceVars;
import ai.datafocus.plugins.qst.rest.JuShuiTanResource;
import ai.datafocus.plugins.qst.rest.TimeStep;
import ai.datafocus.plugins.qst.resultdatatype.Order;
import ai.datafocus.plugins.qst.resultdatatype.OrderQueryResult;
import io.quarkus.logging.Log;

@Dependent
public class CollectLoopingService {

  @Inject Logger log;

  @Inject RabbitmqService rabbitmqService;
  @Inject MyConfig myconfig;

  @Inject JuShuiTanResource jushuitanResource;

  public void startLooping() throws JsonProcessingException {
    InstanceVars jstPartnerData = myconfig.getDcsPluginInstance().getInstanceVars();
    TimeStep timeStep =
        TimeStep.initCreate(
            jstPartnerData.getModified_begin(),
            jstPartnerData.getPage_size(),
            jstPartnerData.getStep_days());
    int loop_times = 0;
    boolean loop_time_limited = jstPartnerData.getLoop_times() > 0;
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
        if (loop_times >= jstPartnerData.getLoop_times()) {
          Log.info("loop_time_limited: " + loop_times + " done");
          break;
        }
      }
    }
  }
}
