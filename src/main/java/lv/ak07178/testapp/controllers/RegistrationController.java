package lv.ak07178.testapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
    @RequestMapping(method = RequestMethod.GET, value = "/registration")
    public String getMainPage(Model model) {
        return "registration";
    }
}
