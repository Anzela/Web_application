package lv.ak07178.testapp.domain;

public class Post {
    private String postText;
    private String postTitle;
    private long postId;
    private long userId;
    private Section section;

    public enum Section {
        NEWS, PUBLIC_DISCUSSIONS, QUESTIONS, JOKES, ART
    }

    public Post(long userId, Section section, long postId, String postTitle, String postText) {
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
}
