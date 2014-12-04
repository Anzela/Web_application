package lv.ak07178.testapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.PostService;

@Controller
public class StartPageController {

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getMainPage(Model model) {
        model.addAttribute("news", postService.getPostsBySection(Post.Section.NEWS));
        model.addAttribute("userPosts", postService.getPostsByType(Post.Section.Type.USERS));
        return "startPage";
    }


}
