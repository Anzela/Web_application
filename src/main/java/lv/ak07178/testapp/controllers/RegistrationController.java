package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ToolbarHelper toolbarHelper;

    @RequestMapping(method = RequestMethod.GET, value = "/registration")
    public String getRegistrationPage(Model model) {
        toolbarHelper.fillDataForToolbar(model);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(Model model,
                          @RequestParam("name") String name,
                          @RequestParam("password") String password) {
        toolbarHelper.fillDataForToolbar(model);
        userService.addUser(name, password, User.Role.USER);
        return "redirect:/users";
    }
}
