package ai.datafocus.plugins.qst.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

@ApplicationScoped
public class HasuraHeaderFactory implements ClientHeadersFactory {

  @Inject
  @ConfigProperty(name = "hasura.admin-secret")
  String hasuraSecret;

  @Override
  public MultivaluedMap<String, String> update(
      MultivaluedMap<String, String> incomingHeaders,
      MultivaluedMap<String, String> clientOutgoingHeaders) {
    MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
    result.add("X-Hasura-admin-secret", hasuraSecret);
    return result;
  }
}
