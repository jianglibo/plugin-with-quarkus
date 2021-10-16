package ai.datafocus.plugins.qst.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/open")
@RegisterRestClient(configKey = "jushuitan-api")
@RegisterClientHeaders(MyRequestHeaderFactory.class)
public interface JuShuiTanService {

  @POST
  @Path("/query.aspx")
//   @Produces(MediaType.APPLICATION_JSON) 
//   OrderQueryResult queryOrder(
  String queryOrder(
      @QueryParam("method") String method,
      @QueryParam("partnerid") String partnerid,
      @QueryParam("token") String token,
      @QueryParam("ts") long ts,
      @QueryParam("sign") String sign, OrderQueryBody orderQueryBody);
}
