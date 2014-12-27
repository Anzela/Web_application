package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Comment;
import lv.ak07178.testapp.services.exceptions.IncorrectRemoveException;
import lv.ak07178.testapp.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommentService {
    private HashMap<Long, Comment> comments = new HashMap<Long, Comment>();
    private long commentId;

    @Autowired
    private CurrentUser currentUser;

    @PostConstruct
    public void init() {
        put(new Comment(3L, 5L, "Лучше бы регистрацию доделали! Сейчас можно зарегать юзера с именем, которое уже есть ;)"));
    }

    private void put(Comment comment) {
        commentId++;
        comment.setId(commentId);
        comments.put(comment.getId(), comment);
    }

    public void addComment(long authorId, long postId, String commentText) {
        if (commentText.isEmpty()) {
            throw new IllegalArgumentException("Empty text");
        }
        Comment comment = new Comment(authorId, postId, commentText);
        put(comment);
    }

    public List<Comment> getAllComments(){
        return new ArrayList<Comment>(comments.values());
    }

    public List<Comment> getCommentsByPostId(long postId) {
        List<Comment> postComments = new ArrayList<Comment>();
        for (Comment comment : getAllComments()) {
            if (comment.getPostId() == postId) {
                postComments.add(comment);
            }
        }
        return postComments;
    }

    public void deleteComment(long commentId) throws IncorrectRemoveException {
        if (comments.remove(commentId)==null) {
            throw new IncorrectRemoveException();
        }
        comments.remove(commentId);
    }

    public void deletePostComments(long postId) throws IncorrectRemoveException {
        List<Comment> postComments = getCommentsByPostId(postId);
        postComments.clear();
    }

    public boolean isCurrentUserIsCommentAuthor(long commentId) {
        Long currentUserId = currentUser.getId();
        if (currentUserId == null) {
            return false;
        }
        return comments.get(commentId).getAuthorId() == currentUserId;
    }

    public Comment getCommentById(long commentId){
        return comments.get(commentId);
    }
}
