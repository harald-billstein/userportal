package billstein.harald.userportal.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;


/**
 * Class handling calls to the login API
 *
 * @author Harald & Stefan
 * @since 2017-12-12
 */
public class LoginWrapper extends AbstractApiConnection {

  /**
   * Check if parameters are valid and prepare user session
   *
   * @param username passed too API
   * @param password passed to API
   * @return URL
   */
  public String login(String username, String password, HttpSession session) {

    HttpURLConnection connection;
    BufferedReader br;
    JSONObject jsonObj;
    String link = "http://localhost:8080/LoginApi/api/login/" + username;
    String token = null;
    String redirectLink = null;
    String output;

    List<RequestPropertyPair> pairs = new ArrayList<RequestPropertyPair>();
    RequestPropertyPair pair = new RequestPropertyPair();
    pair.setKey("password");
    pair.setValue(password);
    pairs.add(pair);
    connection = getConnection(link, "GET", pairs);

    try {

      if (connection.getResponseCode() == 200) {
        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        while ((output = br.readLine()) != null) {
          System.out.println("Response" + output);
          jsonObj = new JSONObject(output);
          token = (String) jsonObj.get("token");
          //session = (HttpSession) context.getExternalContext().getSession(true);
          session.setAttribute("username", username);
          session.setAttribute("token", token);
        }

        redirectLink = "profile?faces-redirect=true";
      }

    } catch (IOException e) {
      e.printStackTrace();
      redirectLink = "login";
    }
    return redirectLink;
  }


  /**
   * Validates user, grants access if token is valid
   */
  public HttpURLConnection validateUser(String username, String token) {

    String link = "http://localhost:8080/LoginApi/api/validate/" + username + "/" + token;
    URL url;
    HttpURLConnection connection = null;
    try {
      url = new URL(link);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return connection;
  }
}
