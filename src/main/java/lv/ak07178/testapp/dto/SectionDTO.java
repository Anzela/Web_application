package lv.ak07178.testapp.dto;

import lv.ak07178.testapp.domain.Post;

public class SectionDTO {

    private int postCount;
    private String title;
    private String description;
    private Post.Section section;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Post.Section getSection() {
        return section;
    }

    public void setSection(Post.Section section) {
        this.section = section;
    }


}
