package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.UserService;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserPageController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public String getUserPage(Model model, @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null ) {
            return "404";
        }
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getPostsByUserId(userId));
        return "userPage";
    }
}
