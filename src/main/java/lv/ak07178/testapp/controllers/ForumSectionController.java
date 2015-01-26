package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Comment;
import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.dto.CommentDTO;
import lv.ak07178.testapp.dto.PostDTO;
import lv.ak07178.testapp.services.PostService;
import lv.ak07178.testapp.services.UserService;
import lv.ak07178.testapp.services.exceptions.*;
import lv.ak07178.testapp.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @RequestMapping(method = RequestMethod.GET, value = "/{section}")
    public String getPostsByFilter(Model model,
                                   @PathVariable Post.Section section) {
        toolbarHelper.fillDataForToolbar(model);
        model.addAttribute("posts", convertToDTOs(postService.getPostsBySection(section)));
        model.addAttribute("sections", postService.getAllSections());
        return "sectionPage";
    }

    private List<PostDTO> convertToDTOs(List<Post> posts) {
        List<PostDTO> result = new ArrayList<PostDTO>();
        for (Post post : posts) {
            result.add(convertToDto(post));
        }
        return result;

    }

    private PostDTO convertToDto(Post post) {
        PostDTO result = new PostDTO();
        result.setAuthorId(post.getAuthorId());
        result.setId(post.getId());
        result.setText(post.getText());
        result.setTitle(post.getTitle());
        result.setCreationDate(post.getCreationDate());
        result.setFormattedCreationDate(postService.getPostCreationDate(post));
        return result;
    }

    @RequestMapping(value = "/{section}/user/{userId}/{postId}")
    public String getPost(Model model, @PathVariable Post.Section section, @PathVariable Long userId, @PathVariable long postId) {
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
                          @RequestParam("postText") String postText) {
        toolbarHelper.fillDataForToolbar(model);
        try {
            postService.addPost(section, postTitle, postText);
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
