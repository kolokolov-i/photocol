package superbro.photocol.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Главная");
        return "index";
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model){
        model.addAttribute("title", "Вход");
        return "login";
    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model){
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = loginedUser.getUsername();
        model.addAttribute("name", userInfo);
        model.addAttribute("title", "Привет");
        return "hello";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        model.addAttribute("title", "Недоступно");
        return "error403";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        return "redirect:/";
    }

//    @GetMapping("/registrate")
//    public String registrate(){
//        return "registrate";
//    }
}
