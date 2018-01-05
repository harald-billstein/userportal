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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Class handling calls to the login API
 *
 * @author Harald & Stefan
 * @since 2017-12-12
 */
@Service
public class LoginWrapper extends AbstractApiConnection {

  /**
   * Check if parameters are valid and prepare user session
   *
   * @param username passed too API
   * @param password passed to API
   * @return URL
   */
  public boolean login(String username, String password, HttpSession session) {

    HttpURLConnection connection;
    BufferedReader br;
    JSONObject jsonObj;
    String link = "http://localhost:8888/login/api/login/" + username;
    String token;
    String output;
    boolean loginSuccess = false;

    List<RequestPropertyPair> pairs = new ArrayList<>();
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
          session.setAttribute("username", username);
          session.setAttribute("token", token);
        }
        loginSuccess = true;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return loginSuccess;
  }


  /**
   * Validates user, grants access if token is valid
   */
  public boolean validateUser(String username, String token) {

    String link = "http://localhost:8888/login/api/validate/" + username + "/" + token;
    URL url;
    HttpURLConnection connection = null;
    boolean accessGranted = false;

    try {
      url = new URL(link);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String output;
        while ((output = br.readLine()) != null) {
          System.out.println("Response" + output);
          JSONObject jsonObj = new JSONObject(output);
          accessGranted = (Boolean) jsonObj.get("userTokenValid");
        }
      } else {
        System.out.println("Session expired!");
        accessGranted = false;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return accessGranted;
  }
}
