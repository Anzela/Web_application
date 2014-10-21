package lv.ak07178.testapp.services;

import org.springframework.stereotype.Service;

import lv.ak07178.testapp.domain.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private HashMap<Long, User> users = new HashMap<Long, User>();

    @PostConstruct
    public void init() {
        users.put(1L, new User("Kirill", 27, User.Gender.MALE, User.Role.MODERATOR));
        users.put(2L, new User("Anzela", 26, User.Gender.FEMALE, User.Role.ADMINISTRATOR));
        users.put(3L, new User("Katja", 28, User.Gender.FEMALE, User.Role.USER));
    }

    public User getUserById(long userId){
        if(users.containsKey(userId)){
            User user = users.get(userId);
            return user;
        }
        return null;
    }

    public Map<Long,User> getAllUsers(){
        return users;
    }
}
