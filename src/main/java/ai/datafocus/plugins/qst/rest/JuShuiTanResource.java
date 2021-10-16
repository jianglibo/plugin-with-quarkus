package ai.datafocus.plugins.qst.rest;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import ai.datafocus.plugins.qst.MyConfig;
import ai.datafocus.plugins.qst.dto.DcsPluginInstance.InstanceVars;
import ai.datafocus.plugins.qst.resultdatatype.OrderQueryResult;

@Dependent
public class JuShuiTanResource {

  private static final String ORDER_METHOD_NAME = "orders.single.query";

  @Inject @RestClient JuShuiTanService jushuitanService;

  @Inject MyConfig myconfig;

  @Inject ObjectMapper mapper;

  public OrderQueryResult getOrders(OrderQueryBody orderQueryBody)
      throws JsonMappingException, JsonProcessingException {
    InstanceVars jstPartnerData = myconfig.getDcsPluginInstance().getInstanceVars();
    long ts = System.currentTimeMillis() / 1000;
    String result =
        jushuitanService.queryOrder(
            ORDER_METHOD_NAME,
            jstPartnerData.getPartnerid(),
            jstPartnerData.getToken(),
            ts,
            JuShuiTanParamsSigner.sign(jstPartnerData, ORDER_METHOD_NAME, ts),
            orderQueryBody);
    return mapper.readValue(result, OrderQueryResult.class);
  }
}
