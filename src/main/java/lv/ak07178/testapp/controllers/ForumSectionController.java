package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.domain.User;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ForumSectionController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private ToolbarHelper toolbarHelper;
    @Autowired
    private FooterHelper footerHelper;

    @RequestMapping(method = RequestMethod.GET, value = "/{section}")
    public String getPostsByFilter(Model model,
                                   @PathVariable Post.Section section) {
        toolbarHelper.fillDataForToolbar(model);
        footerHelper.fillDataForFooter(model);
        model.addAttribute("posts", postService.convertToDTOs(postService.getPostsBySection(section)));
        model.addAttribute("sections", postService.getAllSections());
        return "sectionPage";
    }

    @RequestMapping(value = "/{section}/user/{userId}/{postId}")
    public String getPost(Model model, @PathVariable Long userId, @PathVariable long postId) {
        toolbarHelper.fillDataForToolbar(model);
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
        return "postPage";
    }


    @RequestMapping(value = "/{section}", method = RequestMethod.POST)
    public String addPost(Model model,
                          @PathVariable Post.Section section,
                          @RequestParam("postTitle") String postTitle,
                          @RequestParam("postText") String postText,
                          @RequestParam("file") MultipartFile file) {
        toolbarHelper.fillDataForToolbar(model);
        try {
            postService.addPost(section, postTitle, postText, file);
        } catch (EmptyTextException e) {
            model.addAttribute("postError", "Нельзя создавать тему без текста. Добавьте пожалуйста текст");
        } catch (EmptyTitleException e) {
            model.addAttribute("postError", "Нельзя создавать тему без названия. Добавьте пожалуйста название к теме");
        } catch (IllegalTextSymbolCountException e) {
            model.addAttribute("postError", "Текст слишком длинный. Сделайте его покороче");
        } catch (IllegalTitleSymbolCountException e) {
            model.addAttribute("postError", "Название темы слишком длинное. Сделайте его покороче");
        }
        return getPostsByFilter(model, section);
    }
}
