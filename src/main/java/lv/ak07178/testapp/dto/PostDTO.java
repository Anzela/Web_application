package lv.ak07178.testapp.dto;

import lv.ak07178.testapp.domain.Post;

import java.io.Serializable;

public class PostDTO implements Serializable {
    private String text;
    private String title;
    private long id;
    private long authorId;
    private long creationDate;
    private String formattedCreationDate;
    private int commentCount;
    private int viewCount;
    private byte[] photoBytes;

    public Post.Section getSection() {
        return section;
    }

    public void setSection(Post.Section section) {
        this.section = section;
    }

    private Post.Section section;

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public String getFormattedCreationDate() {
        return formattedCreationDate;
    }

    public void setFormattedCreationDate(String formattedCreationDate) {
        this.formattedCreationDate = formattedCreationDate;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public byte[] getPhotoBytes() {
        return photoBytes;
    }

    public void setPhotoBytes(byte[] photoBytes) {
        this.photoBytes = photoBytes;
    }
}
