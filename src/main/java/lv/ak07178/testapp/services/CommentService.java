package lv.ak07178.testapp.services;

import lv.ak07178.testapp.dao.CommentDao;
import lv.ak07178.testapp.domain.Comment;
import lv.ak07178.testapp.services.exceptions.EmptyTextException;
import lv.ak07178.testapp.services.exceptions.IllegalTextSymbolCountException;
import lv.ak07178.testapp.session.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private static final Logger log = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentDao commentDao;

    private void put(Comment comment) {
        commentDao.addComment(comment);
    }

    public void addComment(long postId, String commentText) throws EmptyTextException, IllegalTextSymbolCountException {
        if (commentText.isEmpty()) {
            throw new EmptyTextException();
        }
        if (commentText.length()>1000){
            throw new IllegalTextSymbolCountException();
        }
        Comment comment = new Comment(currentUser.getId(), postId, commentText);
        put(comment);
    }

    public List<Comment> getAllComments(){
        return commentDao.getAllComments();
    }

    public List<Comment> getCommentsByPostId(long postId) {
        return commentDao.getCommentsByPostId(postId);
    }

    public void deleteComment(long commentId){
        if (isCurrentUserCommentAuthor(commentId)|| userService.isCurrentUserAdmin()) {
            commentDao.deleteCommentById(commentId);
        }
        else {
            log.error("Произошла ошибка при удалении комментария");
        }
    }

    public void deletePostComments(long postId){
        commentDao.deleteCommentsByPostId(postId);
    }

    public boolean isCurrentUserCommentAuthor(long commentId) {
        Long currentUserId = currentUser.getId();
        return currentUserId != null && commentDao.getById(commentId).getAuthorId() == currentUserId;
    }

    public void deleteUserComments(long userId){
        commentDao.deleteUserComments(userId);
    }
}
