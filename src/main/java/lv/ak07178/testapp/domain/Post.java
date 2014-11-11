package lv.ak07178.testapp.domain;

public class Post {
    private String postText;
    private String postTitle;
    private long postId;
    private long userId;
    private PostFilter postFilter;

    public enum PostFilter {
        NEWS, QUESTIONS, SITE, DISCUSSIONS, JOKES, EVENTS, AUTO, ANIMALS, TRAVEL, CINEMA, GAMES, FOOD, SPORT, ART
    }

    public Post(PostFilter postFilter, long userId, String postTitle, String postText) {
        if (postText == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.postFilter = postFilter;
        this.userId = userId;
        this.postTitle = postTitle;
        this.postText = postText;
    }

    public String getPostText() {
        return postText;
    }

    public String getPostTitle() {return postTitle; }

    public long getPostId() {return postId;}

    public long getUserId() {return userId;}

    public PostFilter getPostFilter() {return postFilter;}

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public void setPostFilter(PostFilter postFilter) {
        this.postFilter = postFilter;
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
