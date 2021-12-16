package ai.datafocus.plugins.qst.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import ai.datafocus.plugins.qst.dto.HasuraResponse;

@Path("/v1/graphql")
@RegisterRestClient(configKey = "hasura-rest")
@RegisterClientHeaders(HasuraHeaderFactory.class)
public interface HasuraService {

  @POST
  @Path("/")
  HasuraResponse doGraphql(String body);
}
