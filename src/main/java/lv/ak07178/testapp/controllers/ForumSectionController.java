package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.PostService;
import lv.ak07178.testapp.services.UserService;
import lv.ak07178.testapp.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForumSectionController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrentUser currentUser;

    @RequestMapping(method = RequestMethod.GET, value = "/forum/{section}")
    public String getPostsByFilter(Model model, @PathVariable Post.Section section) {
        model.addAttribute("posts", postService.getPostsByFilter(section));
        model.addAttribute("sections", postService.getAllSections());
        return "forumSection";
    }

    @RequestMapping(value = "/forum/{section}/user/{userId}/{postId}")
    public String getPost(Model model, @PathVariable Post.Section section, @PathVariable Long userId, @PathVariable long postId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return "404";
        }
        model.addAttribute("user", user);

        Post post = postService.getPostById(postId);
        if (post == null) {
            return "404";
        }
        model.addAttribute("post", post);
        return "post";
    }

    @RequestMapping(value = "/forum/{section}", method = RequestMethod.POST)
    public String addPost(Model model,
                          @PathVariable Post.Section section,
                          @RequestParam("postTitle") String postTitle,
                          @RequestParam("postText") String postText) {
        postService.addPost(section, currentUser.getId(), postTitle, postText);
        return getPostsByFilter(model, section);
    }
}
