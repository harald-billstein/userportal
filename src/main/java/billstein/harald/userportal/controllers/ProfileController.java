package billstein.harald.userportal.controllers;

import billstein.harald.userportal.api.LoginWrapper;
import java.net.HttpURLConnection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {


  @RequestMapping({"/", "/profile"})
  public ModelAndView profile(HttpServletRequest request, ModelAndView modelAndView, Model model) {
    System.out.println("Profile controller activated!");

    HttpURLConnection connection;
    Boolean accessGranted = false;

    String userName = (String) request.getSession().getAttribute("username");
    String token = (String) request.getSession().getAttribute("token");

    if (token == null) {
      System.out.println("token null");
      modelAndView.setViewName("forward:/login");
    } else {
      System.out.println("token: " + token);
      //TODO validate token!

      LoginWrapper loginWrapper = new LoginWrapper();
      accessGranted = loginWrapper.validateUser(userName, token);


    }

    if (accessGranted) {
      System.out.println("user validated!");
      modelAndView.setViewName("/profile");
      model.addAttribute("welcome", "Welcome " + userName);
    }

    return modelAndView;

  }

}
