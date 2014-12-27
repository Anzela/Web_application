package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.CommentService;
import lv.ak07178.testapp.services.PostService;
import lv.ak07178.testapp.services.UserService;
import lv.ak07178.testapp.services.exceptions.IncorrectRemoveException;
import lv.ak07178.testapp.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostPageController {

    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ToolbarHelper toolbarHelper;
    @Autowired
    private ForumSectionController forumSectionController;

    @RequestMapping(method = RequestMethod.GET, value = "/{section}/{postId}")
    public String getPostPage(Model model,
                              @PathVariable Post.Section section,
                              @PathVariable Long postId){
        toolbarHelper.fillDataForToolbar(model);
        Post post = postService.getPostById(postId);
        if (post == null ) {
            return "404";
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.getCommentsByPostId(postId));
        model.addAttribute("canDelete",
                postService.isCurrentUserPostAuthor(postId)
                || userService.isCurrentUserAdmin());
        return "post";
    }

    @RequestMapping(value="/{section}/{postId}", method = RequestMethod.POST)
    public String deleteComment(Model model,
                                @PathVariable Post.Section section,
                                @PathVariable Long postId,
                                @RequestParam("commentId") long commentId) {
        try {
            commentService.deleteComment(commentId);
        } catch (IncorrectRemoveException e) {
            model.addAttribute("error", "Объект уже удален");
        }
        return getPostPage(model, section, postId);
    }

    @RequestMapping(value="/{section}/delete", method = RequestMethod.POST)
    public String deletePost(Model model,
                             @PathVariable Post.Section section,
                             @RequestParam("postId") long postId) {
        try {
            postService.deletePost(postId);
        } catch (IncorrectRemoveException e) {
            model.addAttribute("error", "Объект уже удален");
        }
        return "redirect:/" + section;
    }
}
