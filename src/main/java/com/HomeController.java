package com;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS, value="session")
public class HomeController {
    @RequestMapping(value = {"/","/login"},method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        System.out.println("log IN");
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
            System.out.println("in correct ");
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }
    @RequestMapping(value = "/submitlogin",method = RequestMethod.GET)
    public String success(HttpServletRequest request) {
        System.out.println("success");
        return "submitlogin";
    }

    private Object getErrorMessage(HttpServletRequest request, String spring_security_last_exception) {

        Exception exception= (Exception) request.getSession().getAttribute(spring_security_last_exception);
        String error;
        if(exception instanceof BadCredentialsException){
            error="Invalid UserName, pass";
        }else if(exception instanceof LockedException){
            error=exception.getMessage();
        }else{
            error="Invalid UserName or Pass";
        }
        return error;

    }
     @RequestMapping(value = "/403",method = RequestMethod.GET)
     public ModelAndView accessDenied(){
         System.out.println("403");
        ModelAndView modelAndView=new ModelAndView();
         Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
         if(!(authentication instanceof AnonymousAuthenticationToken)){
             modelAndView.addObject("username",authentication.getPrincipal());
         }
         modelAndView.setViewName("403");
        return modelAndView;
     }

    @RequestMapping(value="/logOut", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("log out");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }

}
