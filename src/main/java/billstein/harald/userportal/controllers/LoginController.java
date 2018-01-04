package billstein.harald.userportal.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

  private String userName;
  private String password;

  @RequestMapping("/login")
  public String getLoginPage(Model model) {
    System.out.println("Login controller activated!");

    // TODO if userinput is OK, ready req and send to profile
    return "login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String submitForm(HttpServletRequest request) {
    System.out.println("Login controller: submitForm");
    String redirectPage;

    String userName = request.getParameter("username");
    String password = request.getParameter("password");
    System.out.println(request.getParameter("username"));
    System.out.println(request.getParameter("password"));

    request.setAttribute("username", userName);

    if (userName.equals("test") && password.equals("test")){
      redirectPage = "redirect:/profile";
    } else {
      redirectPage = "login";
    }
    return redirectPage;
  }


}
