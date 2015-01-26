package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.CommentService;
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
    @Autowired
    private ToolbarHelper toolbarHelper;
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public String getUserPage(Model model, @PathVariable Long userId) {
        toolbarHelper.fillDataForToolbar(model);
        User user = userService.getUserById(userId);
        if (user == null ) {
            return "404";
        }
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getPostsByUserId(userId));
        model.addAttribute("canDeleteUser",
                userService.isCurrentUser(userId) || userService.isCurrentUserAdmin());
        for (Post post : postService.getPostsByUserId(userId)) {
            model.addAttribute("data", postService.getPostCreationDate(post));
        }
        return "userPage";
    }

    @RequestMapping(value = "/{section}/{postId}")
    public String getPostFromUserPage(Model model, @PathVariable Post.Section section, @PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        if (post == null ) {
            return "404";
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.getCommentsByPostId(postId));
        return "postPage";
    }
}
