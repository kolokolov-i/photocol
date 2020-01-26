package superbro.photocol.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message, code;
        code = "500";
        message = "Error";
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                code = "404";
                message = "Page not found";
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                code = "403";
                message = "You do not have permission to access this page!";
            }
        }
        model.addAttribute("title", code);
        model.addAttribute("code", code);
        model.addAttribute("message", message);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}