package billstein.harald.userportal.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import billstein.harald.userportal.beans.UserSignupBean;


/**
 * Class handling calls to the login API
 *
 * @author Harald & Stefan
 * @since 2017-12-12
 */
public class RegisterWrapper extends AbstractApiConnection {

  private BufferedReader reader;
  private OutputStreamWriter writer;
  private JSONObject jsonOut;
  private JSONObject jsonIn = null;


  /**
   * Delete a user
   *
   * @param user user
   * @param user token
   * @return URL
   */
  public String deleteUser(String user, String token) {
    String apiUrl = "http://localhost:8888/WebRegisterAPI/v1/delete/" + user + "/" + token;
    System.out.println(apiUrl);
    HttpURLConnection connection;
    boolean success = false;

    connection = getConnection(apiUrl, "DELETE", null);
    success = sendJsonSuccess(connection, "message");

    if (!success) {
      new Exception("Failed to delete user!");
    }
    return "login?faces-redirect=true";
  }


  /**
   * Registers a user
   *
   * @param user created
   * @return URL
   */
  public boolean signup(UserSignupBean user, HttpSession session) {

    String apiUrl = "http://localhost:8888/register/api/register/new/user";
    int responseCode;
    boolean signupSuccess;
    HttpURLConnection connection;

    connection = getConnection(apiUrl, "PUT", null);
    responseCode = sendUserToApi(user, connection);
    signupSuccess = sendJsonSuccess(connection, "registerd");

    if (responseCode == 200 && signupSuccess) {
      setTokenToUserSession(user, session);
    } else {
      signupSuccess = false;
    }
    return signupSuccess;
  }

  private int sendUserToApi(UserSignupBean user, HttpURLConnection connection) {
    Integer responseCode = null;

    try {
      jsonOut = new JSONObject(user);
      writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(jsonOut.toString());
      writer.close();
      responseCode = connection.getResponseCode();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return responseCode;
  }

  private void setTokenToUserSession(UserSignupBean user, HttpSession session) {
    String token = (String) jsonIn.get("token");
    session.setAttribute("username", user.getUserName());
    session.setAttribute("token", token);
  }

  private boolean sendJsonSuccess(HttpURLConnection connection, String key) {
    boolean registrationSuccess = false;
    String output;

    try {
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      while ((output = reader.readLine()) != null) {
        jsonIn = new JSONObject(output);
        jsonIn.get(key);
        registrationSuccess = true;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return registrationSuccess;
  }

}
