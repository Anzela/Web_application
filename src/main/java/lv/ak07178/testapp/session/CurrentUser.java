package lv.ak07178.testapp.session;

public interface CurrentUser {
    String getLogin();
    Long getId();
    void setLogin(String login);
    void setId(Long id);
}