package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Comment;
import lv.ak07178.testapp.services.exceptions.EmptyTextException;
import lv.ak07178.testapp.services.exceptions.IllegalTextSymbolCountException;
import lv.ak07178.testapp.session.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommentService {
    private HashMap<Long, Comment> comments = new HashMap<Long, Comment>();
    private long commentId;
    private static final Logger log = LoggerFactory.getLogger(CommentService.class);
    public static final String DATA_DB = "comments.db";

    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        try {
            log.info("Loading comments");
            FileInputStream fis = new FileInputStream(new File(DATA_DB));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            comments = (HashMap<Long, Comment>) ois.readObject();
            commentId = (Long)ois.readObject();
            ois.close();
            log.info("Loaded comments");
        } catch (IOException e) {
            log.error("Произошла ошибка при сохранении комментариев", e);
        } catch (ClassNotFoundException e) {
            log.error("Произошла ошибка, класс не найден", e);
        }
    }
    @PreDestroy
    public void saveComments() {
        try {
            log.info("Saving comments");
            FileOutputStream fos = new FileOutputStream(new File(DATA_DB));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(comments);
            oos.writeObject((long) commentId);
            oos.close();
            log.info("Saved comments");
        } catch (IOException e) {
            log.error("Произошла ошибка при сохранении комментариев",e);
        }
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
        saveComments();
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
        if (isCurrentUserCommentAuthor(commentId)|| userService.isCurrentUserAdmin()) {
            comments.remove(commentId);
        }
        else {
            log.error("Произошла ошибка при удалении комментария");
        }
    }

    public void deletePostComments(long postId){
        for (Comment comment : getCommentsByPostId(postId)) {
            long commentId = comment.getId();
            comments.remove(commentId);
        }
    }

    public boolean isCurrentUserCommentAuthor(long commentId) {
        Long currentUserId = currentUser.getId();
        return currentUserId != null && comments.get(commentId).getAuthorId() == currentUserId;
    }

    public List<Comment> getCommentsByUserId(long userId) {
        List<Comment> result = new ArrayList<Comment>();
        for (Comment comment: getAllComments()) {
            if (comment.getAuthorId() == userId) {
                result.add(comment);
            }
        }
        return result;
    }

    public void deleteUserComments(long userId){
        for (Comment comment : getCommentsByUserId(userId)) {
            long commentId = comment.getId();
            deleteComment(commentId);
        }
    }
}
