package lv.ak07178.testapp.services;

import lv.ak07178.testapp.services.exceptions.*;
import lv.ak07178.testapp.session.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.ak07178.testapp.domain.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    private HashMap<Long, User> users = new HashMap<Long, User>();
    private HashMap<String, User> usersByName = new HashMap<String, User>();
    private long userId;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    public void init() {
        put("Anzela", "anzelka", User.Role.ADMINISTRATOR);
        put("Kirill", "kirilka", User.Role.MODERATOR);
        put("Katja", "katjuha", User.Role.USER);
    }

    public synchronized User getUserById(long userId){
        return users.get(userId);
    }

    public synchronized User getUserByName(String name) {
        if (name != null) {
            return usersByName.get(name);
        }
        return null;
    }

    public synchronized List<User> getAllUsers(){
        return new ArrayList<User>(users.values());
    }

    public synchronized void addUser(String name, String password, User.Role role)
            throws EmptyTextException, IllegalTextSymbolCountException, ObjectAlreadyExistException {
        if (name.isEmpty() || password.isEmpty()) {
            throw new EmptyTextException();
        }
        if (name.length()<4 || password.length()<4) {
            throw new IllegalTextSymbolCountException();
        }
        if (usersByName.containsKey(name)) {
            throw new ObjectAlreadyExistException();
        }
        put(name, password, role);
    }

    private synchronized void put(String name, String password, User.Role role) {
        User user = new User(name, password, role);
        userId++;
        user.setId(userId);
        users.put(user.getId(), user);
        usersByName.put(user.getName(), user);
    }

    public synchronized void authenticateUser(String name, String password)
            throws UserNotFoundException, IncorrectPasswordException {
        User user = getUserByName(name);
        if (user == null) {
            throw new UserNotFoundException(name);
        }
        if (!user.getPassword().equals(password)){
            throw new IncorrectPasswordException();
        }
    }

    public synchronized boolean isCurrentUserAdmin() {
        Long id = currentUser.getId();
        if (id == null) {
            return false;
        }
        User user = getUserById(id);
        return user.getRole() == User.Role.ADMINISTRATOR;
    }

    public synchronized void deleteUser(long userId) {
        if (isCurrentUser(userId) || isCurrentUserAdmin()) {
            User user = users.get(userId);
            users.remove(userId);
            usersByName.remove(user.getName());
            postService.deleteUserPosts(userId);
            commentService.deleteUserComments(userId);
        }
        else {
            log.error("Произошла ошибка при удалении пользователя");
        }
    }

    public synchronized boolean isCurrentUser(long userId) {
        Long currentUserId = currentUser.getId();
        return currentUserId != null && userId == currentUserId;
    }

    public synchronized int getUserCount() {
        return users.size();
    }
}
