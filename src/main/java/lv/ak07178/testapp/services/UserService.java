package lv.ak07178.testapp.services;

import lv.ak07178.testapp.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.exceptions.IncorrectPasswordException;
import lv.ak07178.testapp.services.exceptions.UserNotFoundException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private CurrentUser currentUser;

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

    public void authenticateUser(String name, String password)
            throws UserNotFoundException, IncorrectPasswordException {
        User user = getUserByName(name);
        if (user == null) {
            throw new UserNotFoundException(name);
        }
        if (user.getPassword().equals(password)){
            return;
        } else {
            throw new IncorrectPasswordException();
        }
    }

    public boolean isCurrentUserAdmin() {
        Long id = currentUser.getId();
        if (id == null) {
            return false;
        }
        User user = getUserById(id);
        return user.getRole() == User.Role.ADMINISTRATOR;
    }
}
