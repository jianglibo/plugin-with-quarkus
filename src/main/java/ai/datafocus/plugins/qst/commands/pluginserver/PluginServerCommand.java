package ai.datafocus.plugins.qst.commands.pluginserver;

import ai.datafocus.plugins.qst.service.MultipartService;
import io.quarkus.logging.Log;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "server", mixinStandardHelpOptions = true)
public class PluginServerCommand {

  @Inject @RestClient MultipartService service;

  @Command(name = "plugin-upload")
  String uploadPlugin(
      @CommandLine.Option(names = "--file", required = true, description = "The file to upload.")
          Path file,
      @CommandLine.Option(
              names = "--plugin-secret",
              required = true,
              description = "the secret of the plugin.")
          String pluginSecret,
      @CommandLine.Option(
              names = "--file-name",
              required = false,
              description = "The file name is different from the real file name.")
          String fileName)
      throws IOException {
    String fn = fileName == null ? file.getFileName().toString() : fileName;

    try (InputStream fileInputStream = Files.newInputStream(file.toAbsolutePath().normalize())) {

      MultipartFormDataOutput multipartFormDataOutput = new MultipartFormDataOutput();

      multipartFormDataOutput.addFormData(
          "file", fileInputStream, MediaType.APPLICATION_OCTET_STREAM_TYPE, fn);
      //
      // multipartFormDataOutput.addFormData("metaData",objectMapper.writeValueAsString(fileMeta),MediaType.APPLICATION_JSON_TYPE);
      // Call client to upload file
      String response = service.sendMultipartData(pluginSecret, multipartFormDataOutput);
      System.out.println("upload result: " + response);
      return response;
    }
  }
}
