package lv.ak07178.testapp.controllers;

import lv.ak07178.testapp.domain.Comment;
import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.dto.CommentDTO;
import lv.ak07178.testapp.services.CommentService;
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

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, value = "/{section}/{postId}")
    public String getPostPage(Model model,
                              @PathVariable Post.Section section,
                              @PathVariable Long postId){
        toolbarHelper.fillDataForToolbar(model);
        Post post = postService.getPostById(postId);
        if (post == null ) {
            return "404_errorPage";
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", convertToDTOs(commentService.getCommentsByPostId(postId)));
        model.addAttribute("canDeletePost",
                postService.isCurrentUserPostAuthor(postId)|| userService.isCurrentUserAdmin());
        model.addAttribute("data", postService.getPostCreationDate(post));
        return "postPage";
    }

    private List<CommentDTO> convertToDTOs(List<Comment> comments) {
        List<CommentDTO> result = new ArrayList<CommentDTO>();
        for (Comment comment : comments) {
            result.add(convertToDto(comment));
        }
        return result;

    }

    private CommentDTO convertToDto(Comment comment) {
        CommentDTO result = new CommentDTO();
        result.setAuthorId(comment.getAuthorId());
        result.setCanDelete(
                commentService.isCurrentUserCommentAuthor(comment.getId()) ||
                        userService.isCurrentUserAdmin()
        );

        result.setId(comment.getId());
        result.setPostId(comment.getPostId());
        result.setText(comment.getText());
        return result;
    }

    @RequestMapping(value = "/{section}/{postId}", method = RequestMethod.POST)
    public String addComment(Model model,
                          @PathVariable Post.Section section,
                          @PathVariable Long postId,
                          @RequestParam("commentText") String commentText) {
        try {
            commentService.addComment(postId, commentText);
        } catch (EmptyTextException e) {
            model.addAttribute("commentError", "Чтобы оставить комментарий, нужно добавить текст");
        } catch (IllegalTextSymbolCountException e) {
            model.addAttribute("commentError", "Текст слишком длинный. Сделайте его покороче");
        }
        return getPostPage(model, section, postId);
    }

    @RequestMapping(value="/{section}/deleteComment", method = RequestMethod.POST)
    public String deleteComment(@PathVariable Post.Section section,
                                @RequestParam("commentId") long commentId) {
            commentService.deleteComment(commentId);
        return "redirect:./";
    }

    @RequestMapping(value="/{section}/delete", method = RequestMethod.POST)
    public String deletePost(@PathVariable Post.Section section,
                             @RequestParam("postId") long postId) {
            postService.deletePost(postId);
        return "redirect:/" + section;
    }
}
