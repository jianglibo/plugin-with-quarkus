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
   * 这里的state信息需要厘清一下。如果当前请求的页是第99页，大小是50，而实际返回的记录只有30条，说明目前没有后继的记录了。
   * 
   * 但是下次请求的时候，还必须是第99页，虽然有30条重复，但是仅仅按页方式处理是没有办法的，除非以offset和limit方式。
   * 
   * 结论：这个状态的管理要根据自己的情况调整策略。
   * 
   * 
   * <pre>
   * {
   *   "state": {...}
   *   "data": [{}, {}]
   * }
   * </pre>
   * 
   * @throws JsonMappingException
   * @throws JsonProcessingException
   */
  public void once() throws JsonMappingException, JsonProcessingException {
    // 这个timestep是控制器通过环境变量传入的。
    TimeStep timeStep = myconfig.getTimeStep();
    OrderQueryResult result =
        jushuitanResource.getOrders(timeStep.toOrderQueryBody());
    TimeStep nextTimeStep = timeStep.nextPage(result.getOrders().size());
    OutOfPlugin outOfPlugin =
        OutOfPlugin.builder()
            .state(nextTimeStep)
            .data(result.getOrders())
            .build();
    appUtil.printOutData(outOfPlugin);
  }
}
