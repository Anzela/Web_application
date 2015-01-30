package lv.ak07178.testapp.dto;

/**
 * @author <a href="mailto:kirill.afanasjev@odnoklassniki.ru">Kirill Afanasjev</a>
 */
public class UserWithName {
    private long id;
    private String name;

    public UserWithName(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserWithName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
