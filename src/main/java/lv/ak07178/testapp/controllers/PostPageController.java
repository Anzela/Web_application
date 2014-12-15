package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Comment;
import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.CommentService;
import lv.ak07178.testapp.services.PostService;
import lv.ak07178.testapp.services.exceptions.IncorrectRemoveException;
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
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ToolbarHelper toolbarHelper;

    @RequestMapping(method = RequestMethod.GET, value = "/{section}/{postId}")
    public String getPostPage(Model model, @PathVariable Post.Section section, @PathVariable Long postId) {
        toolbarHelper.fillDataForToolbar(model);
        Post post = postService.getPostById(postId);
        if (post == null ) {
            return "404";
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.getCommentsByPostId(postId));
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
}
