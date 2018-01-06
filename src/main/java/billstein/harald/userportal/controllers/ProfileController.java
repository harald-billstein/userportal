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

    Boolean accessGranted = false;
    LoginWrapper loginWrapper = new LoginWrapper();

    String userName = (String) request.getSession().getAttribute("username");
    String token = (String) request.getSession().getAttribute("token");

    if (token == null) {
      System.out.println("token null");
      modelAndView.setViewName("redirect:/login");
    } else {
      System.out.println("token: " + token);
      accessGranted = loginWrapper.validateUser(userName, token);
    }

    if (accessGranted) {
      System.out.println("user validated!");
      modelAndView.setViewName("profile");
      model.addAttribute("welcome", "Welcome " + userName);
    }

    return modelAndView;
  }
}
