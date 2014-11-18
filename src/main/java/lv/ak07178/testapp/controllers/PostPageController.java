package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostPageController {

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET, value = "/forum/{section}/{postId}")
    public String getPostPage(Model model, @PathVariable Post.Section section, @PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        if (post == null ) {
            return "404";
        }
        model.addAttribute("post", post);
        model.addAttribute("posts", postService.getPostsByFilter(section));
        return "post";
    }
}
