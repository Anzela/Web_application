package lv.ak07178.testapp.domain;

public class User {

    static final long serialVersionUID = 42L;

    private long id;
    private String name;
    private String password;
    private Role role;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(String name, String password, Role role){
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public enum Role {
        ADMINISTRATOR("Администратор"), MODERATOR("Модератор"), USER("Пользователь");

        private final String title;

        Role(String title){
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
