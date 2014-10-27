package lv.ak07178.testapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import lv.ak07178.testapp.services.UserService;

@Controller
public class UserSearchController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "usersSearch";
    }
}
