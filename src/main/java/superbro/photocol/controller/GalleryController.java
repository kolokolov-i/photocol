package superbro.photocol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Kolokolov Ivan - kolokolov.i@ext-system.com
 * @since 23.01.20
 */
@Controller
public class GalleryController {

    @GetMapping("/albums")
    public String albums(Model model){
        model.addAttribute("title", "Мои альбомы");
        return "albums";
    }
}
