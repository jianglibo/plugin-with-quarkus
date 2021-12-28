package ai.datafocus.plugins.qst.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class PureHttp {

  public static String request(
      String url, String method, Optional<String> body, Map<String, String> headers)
      throws IOException {
    if ("GET".equalsIgnoreCase(method)) {
      return get(url, headers);
    } else if ("POST".equalsIgnoreCase(method)) {
      return postJson(url, body, headers);
    }
    throw new IllegalArgumentException("Invalid method.");
  }

  public static String postJson(String url, Optional<String> body) throws IOException {
    return postJson(url, body, new HashMap<String, String>());
  }

  public static String get(String url, Map<String, String> headers) throws IOException {
    HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
    for (Entry<String, String> entry : headers.entrySet()) {
      con.setRequestProperty(entry.getKey(), entry.getValue());
    }
    con.setRequestMethod("GET"); // PUT is another valid option
    con.setDoOutput(true);
    con.connect();

    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
      StringBuilder response = new StringBuilder();
      String responseLine = null;
      while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
      }
      return response.toString();
    }
  }

  public static String postJson(String url, Optional<String> body, Map<String, String> headers)
      throws IOException {
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

    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
      StringBuilder response = new StringBuilder();
      String responseLine = null;
      while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
      }
      return response.toString();
    }
  }
}
