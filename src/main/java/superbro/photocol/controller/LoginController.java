package superbro.photocol.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        model.addAttribute("title", "Вход");
        return "login";
    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("message", errorMessage);
        return "login";
    }

//    @GetMapping("/hello")
//    public String hello(Principal principal, Model model) {
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
//        String userInfo = loginedUser.getUsername();
//        model.addAttribute("name", userInfo);
//        model.addAttribute("title", "Привет");
//        return "hello";
//    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        return "redirect:/";
    }

    @GetMapping("/registrate")
    public String registrate(Model model) {
        model.addAttribute("title", "Регистрация");
        return "registrate";
    }
}
