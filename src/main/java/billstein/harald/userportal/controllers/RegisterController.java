package billstein.harald.userportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

  @RequestMapping("/register")
  public String register(Model model) {
    System.out.println("Register controller activated!");
    // if
    return "register";
  }

}
