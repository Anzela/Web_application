package lv.ak07178.testapp.session;

public interface CurrentUser {
    String getName();
    Long getId();
    void setName(String name);
    void setId(Long id);
}