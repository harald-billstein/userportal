package billstein.harald.userportal.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * Class handling connections
 *
 * @author Harald & Stefan
 * @since 2017-12-14
 */
public abstract class AbstractApiConnection {


  /**
   * Opens and returns a connection to the specified apiUrl
   *
   * @return open connection
   */
  public HttpURLConnection getConnection(String apiUrl, String method,
      List<RequestPropertyPair> pairs) {
    URL url;
    HttpURLConnection connection = null;
    try {
      url = new URL(apiUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod(method);
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Accept", "application/json");

      if (pairs != null) {
        for (RequestPropertyPair pair : pairs) {
          connection.setRequestProperty(pair.getKey(), pair.getValue());
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return connection;
  }

}
