package ai.datafocus.plugins.qst.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;

public class PureHttp {

  public static RequestResult request(
      String url, String method, Optional<String> body, Map<String, String> headers)
      throws IOException {
    if ("GET".equalsIgnoreCase(method)) {
      return get(url, headers);
    } else if ("POST".equalsIgnoreCase(method)) {
      return postJson(url, body, headers);
    }
    throw new IllegalArgumentException("Invalid method.");
  }

  public static RequestResult postJson(String url, Optional<String> body) throws IOException {
    return postJson(url, body, new HashMap<String, String>());
  }

  public static RequestResult get(String url, Map<String, String> headers) throws IOException {
    HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
    for (Entry<String, String> entry : headers.entrySet()) {
      con.setRequestProperty(entry.getKey(), entry.getValue());
    }
    con.setRequestMethod("GET"); // PUT is another valid option
    con.connect();
    int code = con.getResponseCode();
    String body;
    try {
      body = readStream(con).orElse("");
    } catch (Exception e) {
      StringWriter sw = new StringWriter();
      PrintWriter w = new PrintWriter(sw);
      e.printStackTrace(w);
      body = sw.toString();
    }
    return RequestResult.builder().statusCode(code).responseBody(body).build();
  }

  @Data
  @Builder
  public static class RequestResult {
    private int statusCode;
    private String responseBody;

    public void printMessage(int expectedHttpStatus, boolean printBody) {
      if (statusCode != expectedHttpStatus) {
        System.out.println(
            String.format(
                "expected HTTP status code: %s, the return code: %s",
                expectedHttpStatus, statusCode));
        System.out.println("the response body is as bellow:\n");
        System.out.println(responseBody);
      } else {
        System.out.println("return expected code: " + statusCode);
        if (printBody) {
          System.out.println("the response body is as bellow:\n");
          System.out.println(responseBody);
        }
      }
    }
  }

  private static Optional<String> readStream(HttpURLConnection con) throws IOException {
    Optional<String> v = readStream(con.getInputStream());
    if (v.isEmpty()) {
      return readStream(con.getErrorStream());
    } else {
      return v;
    }
  }

  private static Optional<String> readStream(InputStream is) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"))) {
      StringBuilder responseBody = new StringBuilder();
      String responseLine = null;
      while ((responseLine = br.readLine()) != null) {
        responseBody.append(responseLine.trim());
      }
      String body = responseBody.toString().trim();
      return body.isEmpty() ? Optional.empty() : Optional.of(body);
    } catch (IOException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }

  public static RequestResult postJson(
      String url, Optional<String> body, Map<String, String> headers) throws IOException {
    HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
    con.setRequestProperty("Accept", "application/json");
    for (Entry<String, String> entry : headers.entrySet()) {
      con.setRequestProperty(entry.getKey(), entry.getValue());
    }
    con.setRequestMethod("POST"); // PUT is another valid option
    con.setDoOutput(true);
    if (body.isPresent()) {
      byte[] out = body.get().getBytes(StandardCharsets.UTF_8);
      int length = out.length;

      con.setFixedLengthStreamingMode(length);
      con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
      con.connect();
      try (OutputStream os = con.getOutputStream()) {
        os.write(out);
      }
    }
    int code = con.getResponseCode();
    String rbody =
        readStream(con.getInputStream())
            .orElseGet(() -> readStream(con.getErrorStream()).orElse(""));
    return RequestResult.builder().statusCode(code).responseBody(rbody).build();
  }
}
