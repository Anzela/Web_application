package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.UserService;
import lv.ak07178.testapp.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserRemovePageController {

    @Autowired
    private UserService userService;
    @Autowired
    private ToolbarHelper toolbarHelper;
    @Autowired
    private CurrentUser currentUser;

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}/delete")
    public String getUserRemovePage(Model model, @PathVariable Long userId) {
        toolbarHelper.fillDataForToolbar(model);
        User user = userService.getUserById(userId);
        if (user == null ) {
            return "404";
        }
        return "userRemovePage";
    }

    @RequestMapping(value="/user/{userId}/delete", method = RequestMethod.POST)
    public String deleteUser(HttpSession session, @PathVariable Long userId) {
        userService.deleteUser(userId);
        if (userId.equals(currentUser.getId())) {
            session.invalidate();
            return "redirect:/login";
        }
        return "redirect:/";
    }
}
