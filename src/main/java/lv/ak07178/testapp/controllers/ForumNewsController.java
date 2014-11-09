package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForumNewsController {

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET, value = "/forum/NEWS")
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getPostsBySection(Post.Section.NEWS));
        return "forumSection";
    }

    @RequestMapping(value = "/forum/NEWS", method = RequestMethod.POST)
    public String addNews(Model model,
                          @PathVariable long userId,
                          @PathVariable Post.Section section,
                          @PathVariable long postId,
                          @RequestParam("postTitle") String postTitle,
                          @RequestParam("postText") String postText) {
        postService.addPost(userId, section, postId, postTitle, postText);
        return "forumSection";
    }
}
