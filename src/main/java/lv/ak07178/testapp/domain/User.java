package lv.ak07178.testapp.domain;

public class User {
    private long id;
    private String name;
    private Integer age;
    private Gender gender;
    private Role role;

    public enum Gender {
        MALE, FEMALE
    }

    public enum Role {
        ADMINISTRATOR, MODERATOR, USER
    }

    public User(long id, String username, Integer age, Gender gender, Role role){
        this.id = id;
        this.name = username;
        this.age = age;
        this.gender = gender;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getName() {return name;}

    public Integer getAge() {return age;}

    public Gender getGender() {return gender;}

    public Role getRole() {return role;}

}
