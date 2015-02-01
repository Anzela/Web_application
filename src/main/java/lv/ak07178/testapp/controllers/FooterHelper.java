package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.services.CommentService;
import lv.ak07178.testapp.services.PostService;
import lv.ak07178.testapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class FooterHelper {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    public void fillDataForFooter(Model model) {
        model.addAttribute("usersCount", userService.getAllUsers().size());
        model.addAttribute("postsCount", postService.getAllPosts().size());
        model.addAttribute("commentsCount", commentService.getAllComments().size());
    }
}
