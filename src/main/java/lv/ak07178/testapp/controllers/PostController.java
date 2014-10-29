package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

        @RequestMapping(method = RequestMethod.GET, value = "/post")
        public String getAllUsers(Model model) {
            model.addAttribute("users", postService.getAllPosts());
            return "usersSearch";
        }
    }

