package superbro.photocol.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Authentication authentication) {
        if(authentication != null && authentication.isAuthenticated()){
            return "redirect:/albums";
        }
        else {
            return "redirect:/login";
        }
    }

}
