package billstein.harald.userportal.controllers;

import billstein.harald.userportal.api.RegisterWrapper;
import billstein.harald.userportal.beans.UserSignupBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

  @RequestMapping("/register")
  public String getRegisterPage() {
    System.out.println("Register controller activated!");
    // if
    return "register";
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ModelAndView register(HttpServletRequest request, ModelAndView modelAndView) {
    System.out.println("Register controller: submitForm");

    RegisterWrapper registerWrapper = new RegisterWrapper();
    UserSignupBean newUser = new UserSignupBean();
    HttpSession session = request.getSession();

    newUser.setUserName(request.getParameter("username"));
    newUser.setFirstName(request.getParameter("firstname"));
    newUser.setLastName(request.getParameter("lastname"));
    newUser.setPassword(request.getParameter("password"));
    newUser.setEmail(request.getParameter("email"));
    System.out.println(newUser.toString());
    registerWrapper.signup(newUser,session);




    modelAndView.setViewName("forward:/profile");
    return modelAndView;


  }

}
