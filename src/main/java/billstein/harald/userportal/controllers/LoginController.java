package billstein.harald.userportal.controllers;

import billstein.harald.userportal.api.LoginWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  @RequestMapping("/login")
  public String getLoginPage() {
    System.out.println("Login controller activated!");
    return "login";
  }

  @RequestMapping("/getRegisterPage")
  public ModelAndView getRegisterPage(ModelAndView modelAndView) {
    modelAndView.setViewName("redirect:/register");
    return modelAndView;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ModelAndView submitForm(HttpServletRequest request, ModelAndView modelAndView,
      Model model) {
    System.out.println("Login controller: submitForm");

    LoginWrapper loginWrapper = new LoginWrapper();
    HttpSession session = request.getSession();
    boolean loginSuccess;

    String userName = request.getParameter("username");
    String password = request.getParameter("password");

    System.out.println("username: " + userName);
    System.out.println("password: " + password);

    loginSuccess = loginWrapper.login(userName, password, session);
    if (loginSuccess) {
      modelAndView.setViewName("redirect:/profile");
    } else {
      modelAndView.setViewName("login");
      model.addAttribute("error", "Wrong Credential");
    }

    return modelAndView;
  }
}
