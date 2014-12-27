package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartPageController {

    @Autowired
    private PostService postService;
    @Autowired
    private ToolbarHelper toolbarHelper;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getAdminSections(Model model) {
        toolbarHelper.fillDataForToolbar(model);
        model.addAttribute("adminSections", postService.getSectionsByType(Post.Section.Type.ADMIN));
        model.addAttribute("userSections", postService.getSectionsByType(Post.Section.Type.USERS));
        return "startPage";
    }

}
