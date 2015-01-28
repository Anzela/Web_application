package lv.ak07178.testapp.domain;

import java.io.Serializable;

public class Comment implements Serializable {
    private String text;
    private long id;
    private long authorId;
    private long postId;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public Comment(long authorId, long postId, String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.authorId = authorId;
        this.postId = postId;
        this.text = text;
    }
}
