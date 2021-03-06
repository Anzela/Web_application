package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.services.UserService;
import lv.ak07178.testapp.services.exceptions.IncorrectPasswordException;
import lv.ak07178.testapp.services.exceptions.UserNotFoundException;
import lv.ak07178.testapp.session.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private ToolbarHelper toolbarHelper;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String getLoginPage(HttpSession session) {
        session.invalidate();
        return "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(
            @RequestParam("name") String name,
            @RequestParam("password") String password, Model model) {
        log.info("Logging with name " + name);
        log.info("Logging with password " + password);
        toolbarHelper.fillDataForToolbar(model);
        try {
            userService.loginUser(name, password);
            return "redirect:/";
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Не найден пользователь: " + e.getLogin());
        } catch (IncorrectPasswordException e) {
            model.addAttribute("error", "Неправильный пароль");
        }
        return "loginPage";
    }
}
