package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.PostService;
import lv.ak07178.testapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForumFilterController {

    @Autowired
    private PostService postService;
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/forum/{postFilter}")
    public String getPostsByFilter(Model model, @PathVariable Post.PostFilter postFilter) {
        model.addAttribute("posts", postService.getPostsByFilter(postFilter));
        return "forumWithFilter";
    }

    @RequestMapping(value = "/forum/{postFilter}/user/{userId}/{postId}")
    public String getPost(Model model, @PathVariable Post.PostFilter postFilter, @PathVariable Long userId, @PathVariable long postId) {
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

    @RequestMapping(value = "/forum/{postFilter}", method = RequestMethod.POST)
    public String addPost(Model model,
                          @PathVariable Post.PostFilter postFilter,
                          @RequestParam("postTitle") String postTitle,
                          @RequestParam("postText") String postText) {
        postService.addPost(postFilter, 1l, postTitle, postText);
        return getPostsByFilter(model, postFilter);
    }
}
