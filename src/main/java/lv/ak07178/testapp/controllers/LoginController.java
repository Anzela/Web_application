package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.dto.LoginInfo;
import lv.ak07178.testapp.services.UserService;
import lv.ak07178.testapp.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
    public String loginProcess(@ModelAttribute("loginInfo")LoginInfo loginInfo) {
        Boolean isValid = userService.authenticateUser(loginInfo.getName(), loginInfo.getPassword());
        if (isValid) {
            User user = userService.getUserByName(loginInfo.getName());
            currentUser.setLogin(loginInfo.getName());
            //currentUser.setId(user.getId());
            return "redirect:/";
        }
        return "404";
    }
}
