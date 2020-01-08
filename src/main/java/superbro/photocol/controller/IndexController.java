package superbro.photocol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

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
//        CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//        CsrfToken token = (CsrfToken) session.getAttribute(CsrfToken.class.getName());
//        model.addAttribute("_csrf", token.getToken());
        return "login";
    }

    @PostMapping("/login")
    public String loginAction(/*RedirectAttributes attr*/){
        //attr.addFlashAttribute("message", "Неверный пароль");
        return "hello";
    }

    @GetMapping("/registrate")
    public String registrate(){
        return "registrate";
    }
}
