package lv.ak07178.testapp.domain;

public class Post {
    private String postText;
    private String postTitle;
    private long postId;
    private long userId;
    private Section section;

    public enum Section {
        NEWS, QUESTIONS, SITE, DISCUSSIONS, JOKES, EVENTS, AUTO, ANIMALS, TRAVEL, CINEMA, GAMES, FOOD, SPORT, ART
    }

    public Post(long userId, Section section, long postId, String postTitle, String postText) {
        if (postText == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.userId = userId;
        this.section = section;
        this.postId = postId;
        this.postTitle = postTitle;
        this.postText = postText;
    }

    public String getPostText() {
        return postText;
    }

    public String getPostTitle() {return postTitle; }

    public long getPostId() {return postId;}

    public long getUserId() {return userId;}

    public Section getSection() {return section;}

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
}
