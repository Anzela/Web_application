package lv.ak07178.testapp.domain;

public class User {
    private String name;
    private String password;

    //public User(String username, String password){
    //    this.name = username;
    //    this.password = password;
    //}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
