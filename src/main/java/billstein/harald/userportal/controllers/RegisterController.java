package billstein.harald.userportal.controllers;

import billstein.harald.userportal.api.RegisterWrapper;
import billstein.harald.userportal.beans.UserSignupBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

  @RequestMapping("/register")
  public String getRegisterPage() {
    System.out.println("Register controller activated!");
    return "register";
  }

  @RequestMapping("/getLoginPage")
  public String getLoginPage() {
    return "redirect:/login";
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ModelAndView register(HttpServletRequest request, ModelAndView modelAndView, Model model) {
    System.out.println("Register controller: submitForm");

    RegisterWrapper registerWrapper = new RegisterWrapper();
    UserSignupBean newUser = new UserSignupBean();
    HttpSession session = request.getSession();
    boolean signupSuccess = false;

    newUser.setUserName(request.getParameter("username"));
    newUser.setFirstName(request.getParameter("firstname"));
    newUser.setLastName(request.getParameter("lastname"));
    newUser.setPassword(request.getParameter("password"));
    newUser.setEmail(request.getParameter("email"));

    signupSuccess = registerWrapper.signup(newUser, session);
    System.out.println("User signed up: " + signupSuccess + " user details: " + newUser.toString());

    if(signupSuccess) {
      modelAndView.setViewName("redirect:/profile");
    } else {
      model.addAttribute("error", "Something went wrong");
      modelAndView.setViewName("register");
    }

    return modelAndView;
  }
}
