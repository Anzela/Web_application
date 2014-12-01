package lv.ak07178.testapp.services;

import org.springframework.stereotype.Service;
import lv.ak07178.testapp.domain.User;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    private HashMap<Long, User> users = new HashMap<Long, User>();
    private HashMap<String, User> usersByName = new HashMap<String, User>();
    private long userId;

    @PostConstruct
    public void init() {
        put("Anzela", "anzelka", User.Role.ADMINISTRATOR);
        put("Kirill", "kirilka", User.Role.MODERATOR);
        put("Katja", "katjuha", User.Role.USER);
    }

    public User getUserById(long userId){
        return users.get(userId);
    }

    public User getUserByName(String name) {
        if (name != null) {
            return usersByName.get(name);
        }
        return null;
    }

    public List<User> getAllUsers(){
        return new ArrayList<User>(users.values());
    }

    public void addUser(String name, String password, User.Role role) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Empty name");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Empty password");
        }
        put(name, password, role);
    }

    private void put(String name, String password, User.Role role) {
        User user = new User(name, password, role);
        userId++;
        user.setId(userId);
        if (users.containsKey(user.getId())) {
            throw new IllegalArgumentException("User with id " + user.getId() + " already exist");
        }
        users.put(user.getId(), user);
        usersByName.put(user.getName(), user);
    }

    public boolean authenticateUser(String name, String password) {
        User user = getUserByName(name);
        if (user!=null && user.getPassword().equals(password)){
            return true;
        }
        else {
            throw new IllegalArgumentException("User doesn't exists");
        }
    }
}
