package lv.ak07178.testapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{userId}")
    public String getMainPage(Model model, @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null ) {
            return "404";
        }
        model.addAttribute("name", user.getName());
        model.addAttribute("gender", user.getGender());
        model.addAttribute("age", user.getAge());
        model.addAttribute("role", user.getRole());
        return "userPage";
    }
}
