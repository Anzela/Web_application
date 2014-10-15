package lv.ak07178.testapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class FrontPageController {

     @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getMainPage(Model model) {
        return "startPage";
    }


}
