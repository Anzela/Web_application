package lv.ak07178.testapp.domain;

public class Post {
    private String text;
    private String title;
    private long id;
    private long authorId;
    private Section section;

    public enum Section {
        NEWS, QUESTIONS, SITE, DISCUSSIONS, JOKES, EVENTS, AUTO, ANIMALS, TRAVEL, CINEMA, GAMES, FOOD, SPORT, ART
    }

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

    public Section getSection() {
        return section;
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

    public void setSection(Section section) {
        this.section = section;
    }

    public Post(Section section, long authorId, String title, String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.section = section;
        this.authorId = authorId;
        this.title = title;
        this.text = text;
    }
}
