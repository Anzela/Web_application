package lv.ak07178.testapp.domain;

public class User {
    private long id;
    private String userName;
    private String password;
    private Role role;


    public enum Role {
        ADMINISTRATOR("Администратор"), MODERATOR("Модератор"), USER("Пользователь");

        private final String roleTitle;

        Role(String roleTitle){
            this.roleTitle = roleTitle;
        }

        public String getRoleTitle() {
            return roleTitle;
        }
    }

    public User(String userName, String password, Role role){
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {return userName;}

    public Role getRole() {return role;}

    public void setId(long id) {this.id = id;}

    public void setPassword(String password) {this.password = password;}
}
