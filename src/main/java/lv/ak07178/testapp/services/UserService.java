package lv.ak07178.testapp.services;

import org.springframework.stereotype.Service;

import lv.ak07178.testapp.domain.User;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private HashMap<Long, User> users = new HashMap<Long, User>();

    @PostConstruct
    public void init() {
        put(new User(1L, "Kirill", 27, User.Gender.MALE, User.Role.MODERATOR));
        put(new User(2l, "Anzela", 26, User.Gender.FEMALE, User.Role.ADMINISTRATOR));
        put(new User(3l, "Katja", 28, User.Gender.FEMALE, User.Role.USER));
    }

    private void put(User user) {
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
}
