package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Comment;
import lv.ak07178.testapp.services.exceptions.EmptyTextException;
import lv.ak07178.testapp.services.exceptions.EmptyTitleException;
import lv.ak07178.testapp.services.exceptions.IllegalTextSymbolCountException;
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

    public void addComment(long postId, String commentText) throws EmptyTextException, IllegalTextSymbolCountException {
        if (commentText.isEmpty()) {
            throw new EmptyTextException();
        }
        if (commentText.length()>5000){
            throw new IllegalTextSymbolCountException();
        }
        Comment comment = new Comment(currentUser.getId(), postId, commentText);
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

    public void deleteComment(long commentId){
        comments.remove(commentId);
    }

    public void deletePostComments(long postId){
        for (Comment comment : getCommentsByPostId(postId)) {
            comments.remove(comment.getId());
            }
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
