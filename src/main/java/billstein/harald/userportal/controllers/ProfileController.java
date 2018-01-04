package billstein.harald.userportal.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {

  @RequestMapping({"/","/profile"})
  public String profile(HttpServletRequest request, Model model) {
    System.out.println("Profile controller activated!");

    System.out.println("Username passed along" + request.getAttribute("username"));

    String redirectPage = "redirect:/login";

    return redirectPage;
  }

}
