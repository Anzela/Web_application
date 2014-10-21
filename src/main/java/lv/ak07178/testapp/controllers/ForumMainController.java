package lv.ak07178.testapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForumMainController {
    @RequestMapping(method = RequestMethod.GET, value = "/forum")
    public String getForumMainPage(Model model) {
        return "forum";
    }
}
