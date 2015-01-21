package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.UserService;
import lv.ak07178.testapp.services.exceptions.EmptyTextException;
import lv.ak07178.testapp.services.exceptions.IllegalTextSymbolCountException;
import lv.ak07178.testapp.services.exceptions.ObjectAlreadyExistException;
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
        return "registrationPage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(Model model,
                          @RequestParam("name") String name,
                          @RequestParam("password") String password) {
        toolbarHelper.fillDataForToolbar(model);
        try {
            userService.addUser(name, password, User.Role.USER);
            return "redirect:/login";
        } catch (EmptyTextException e) {
            model.addAttribute("error", "Нужно заполнить все поля");
        } catch (IllegalTextSymbolCountException e) {
            model.addAttribute("error", "В логине и пароле должно быть не меньше 4-х символов");
        } catch (ObjectAlreadyExistException e) {
            model.addAttribute("error", "Такой пользователь уже существует, придумайте другой логин");
        }
        return "registrationPage";
    }
}
