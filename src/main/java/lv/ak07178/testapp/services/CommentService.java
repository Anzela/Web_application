package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Comment;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    private HashMap<Long, Comment> comments = new HashMap<Long, Comment>();
    private long commentId;

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

    public List<Comment> getCommentsByPostId(long postId) {
        List<Comment> postComments = new ArrayList<Comment>();
        for (Map.Entry entry : comments.entrySet()) {
            Comment value = (Comment) entry.getValue();
            if (value.getPostId() == postId) {
                postComments.add(value);
            }
        }
        return postComments;
    }
}
