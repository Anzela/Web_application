package lv.ak07178.testapp.services.exceptions;

/**
 * @author <a href="mailto:kirill.afanasjev@odnoklassniki.ru">Kirill Afanasjev</a>
 */
public class UserNotFoundException extends Exception {
    private String login;

    public UserNotFoundException(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
