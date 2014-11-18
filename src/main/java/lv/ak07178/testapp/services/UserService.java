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
    private long userId;

    @PostConstruct
    public void init() {
        put(new User("Anzela", "anzelka", User.Role.ADMINISTRATOR));
        put(new User("Kirill", "kirilka", User.Role.MODERATOR));
        put(new User("Katja", "katjuha", User.Role.USER));
    }

    private void put(User user) {
        userId++;
        user.setId(userId);
        if (users.containsKey(user.getId())) {
            throw new IllegalArgumentException("User with id " + user.getId() + " already exist");
        }
        users.put(user.getId(), user);
    }

    public User getUserById(long userId){
        return users.get(userId);
    }

    public List<User> getAllUsers(){
        return new ArrayList<User>(users.values());
    }

    public void addUser(String userName, String password, User.Role role) {
        if (userName.isEmpty()) {
            throw new IllegalArgumentException("Empty name");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Empty password");
        }
        User user = new User(userName, password, role);
        put(user);
    }
}
