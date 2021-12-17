package ai.datafocus.plugins.qst.service;

import ai.datafocus.plugins.qst.CommonNames;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

@Path("/upload")
@RegisterRestClient(configKey = "upload")
public interface MultipartService {

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.TEXT_PLAIN)
  String sendMultipartData(
      @HeaderParam(CommonNames.UPLOAD_SECRET_HEADER_NAME) String dcsPluginSecret,
      @MultipartForm MultipartFormDataOutput data);
}
