package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.PostService;
import lv.ak07178.testapp.services.UserService;
import lv.ak07178.testapp.services.exceptions.EmptyTextException;
import lv.ak07178.testapp.services.exceptions.EmptyTitleException;
import lv.ak07178.testapp.services.exceptions.IllegalTextSymbolCountException;
import lv.ak07178.testapp.services.exceptions.IllegalTitleSymbolCountException;
import lv.ak07178.testapp.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostEditController {

    @Autowired
    private UserService userService;
    @Autowired
    private ToolbarHelper toolbarHelper;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET, value = "/{section}/{postId}/editPost")
    public String getPostEditPage(Model model,
                                  @PathVariable Long postId) {
        toolbarHelper.fillDataForToolbar(model);
        Post post = postService.getPostById(postId);
        if (post == null ) {
            return "404_errorPage";
        }
        model.addAttribute("post", post);
        return "postEditPage";
    }

    @RequestMapping(value = "/{section}/{postId}/editPost", method = RequestMethod.POST)
    public String editPost(Model model,
                           @PathVariable long postId,
                           @PathVariable Post.Section section,
                           @RequestParam("postTitle") String newPostTitle,
                           @RequestParam("postText") String newPostText) {
        Post post = postService.getPostById(postId);
        try {
            postService.editPost(post, newPostTitle, newPostText);
        } catch (EmptyTextException e) {
            model.addAttribute("postError", "Нельзя создавать тему без текста. Добавьте пожалуйста текст");
            return "postEditPage";
        } catch (EmptyTitleException e) {
            model.addAttribute("postError", "Нельзя создавать тему без названия. Добавьте пожалуйста название к теме");
            return "postEditPage";
        } catch (IllegalTextSymbolCountException e) {
            model.addAttribute("postError", "Текст слишком длинный. Сделайте его покороче");
            return "postEditPage";
        } catch (IllegalTitleSymbolCountException e) {
            model.addAttribute("postError", "Название темы слишком длинное. Сделайте его покороче");
            return "postEditPage";
        }
        return "redirect:/" + section + "/" + postId;
    }
}
