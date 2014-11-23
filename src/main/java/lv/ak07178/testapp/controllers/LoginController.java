package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.services.UserService;
import lv.ak07178.testapp.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private CurrentUser currentUser;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(
            @RequestParam("name") String name,
            @RequestParam("password") String password) {
        //if(userService.getUserByName(name) != null && userService.getUserByName(name).getPassword().equals(password)) {
        currentUser.setName(name);
        currentUser.setId(userService.getUserByName(name).getId());
        //}
        return "redirect:/users";
    }
}
