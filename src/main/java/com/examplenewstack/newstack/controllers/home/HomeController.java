package com.examplenewstack.newstack.controllers.home;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/v1")
public class HomeController {

    @GetMapping("/home")
    public ModelAndView homeScreen(HttpSession session) {
        if (!isUserAuthenticated(session)) {
            return new ModelAndView("redirect:/v1/login");
        }

        return new ModelAndView("homeAdm");
    }

    private boolean isUserAuthenticated(HttpSession session) {
        return session.getAttribute("LoginFeito") != null &&
                Boolean.TRUE.equals(session.getAttribute("LoginFeito")) &&
                session.getAttribute("usuarioID") != null;
    }
}