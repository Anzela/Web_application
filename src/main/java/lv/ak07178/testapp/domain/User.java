package lv.ak07178.testapp.domain;

public class User {
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

    public User(String username, Integer age, Gender gender, Role role){
        this.name = username;
        this.age = age;
        this.gender = gender;
        this.role = role;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {return age;}

    public void setAge(Integer age) {this.age = age;}

    public Gender getGender() {return gender;}

    public void setGender(Gender gender) {this.gender = gender;}

    public void setRole(Role role) {this.role = role;}

    public Role getRole() {return role;}

}
