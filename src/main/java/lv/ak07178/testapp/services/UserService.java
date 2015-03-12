package lv.ak07178.testapp.services;

import lv.ak07178.testapp.dao.UserDao;
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

    @Autowired
    private UserDao userDao;

    private HashMap<Long, User> users = new HashMap<Long, User>();
    private HashMap<String, User> usersByName = new HashMap<String, User>();
    private long userId;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    public void init() {
        put("Anzela", "anzelka", "Anzela_k@mail.ru", User.Role.ADMINISTRATOR);
        put("Kirill", "kirilka", "Kirill_kirilka@mail.ru", User.Role.MODERATOR);
        put("Katja", "katjuha", "Katja_katjuha@mail.ru", User.Role.USER);
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

    public synchronized void addUser(String name, String password, String passwordRepeat, String email, User.Role role)
            throws EmptyTextException, IllegalTextSymbolCountException, ObjectAlreadyExistException, IncorrectPasswordException {
        if (name.isEmpty() || password.isEmpty()) {
            throw new EmptyTextException();
        }
        if (name.length()<5 || password.length()<5 || name.length()>20 || password.length()>20) {
            throw new IllegalTextSymbolCountException();
        }
        if (usersByName.containsKey(name)) {
            throw new ObjectAlreadyExistException();
        }
        if (!password.equals(passwordRepeat)) {
            throw new IncorrectPasswordException();
        }
        put(name, password, email, role);
    }

    private synchronized void put(String name, String password, String email, User.Role role) {
        User user = new User(name, password, email, role);
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

    public void loginUser(String name, String password) throws UserNotFoundException, IncorrectPasswordException {
        authenticateUser(name, password);
        currentUser.setName(name);
        User userByName = getUserByName(name);
        long id = userByName.getId();
        currentUser.setId(id);
        log.info("Logging with id " + id);
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
