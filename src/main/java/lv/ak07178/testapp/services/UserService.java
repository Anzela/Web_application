package lv.ak07178.testapp.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.ak07178.testapp.dao.UserDao;
import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.exceptions.EmptyTextException;
import lv.ak07178.testapp.services.exceptions.IllegalTextSymbolCountException;
import lv.ak07178.testapp.services.exceptions.IncorrectPasswordException;
import lv.ak07178.testapp.services.exceptions.ObjectAlreadyExistException;
import lv.ak07178.testapp.services.exceptions.UserNotFoundException;
import lv.ak07178.testapp.session.CurrentUser;

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

    private long userId;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    public void init() {
    }

    public synchronized User getUserById(long userId){
        return userDao.getUserById(userId);
    }

    public synchronized User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public synchronized List<User> getAllUsers(){
        return new ArrayList<User>(userDao.getAllUsers());
    }

    public synchronized void addUser(String name, String password, String passwordRepeat, String email, User.Role role)
            throws EmptyTextException, IllegalTextSymbolCountException, ObjectAlreadyExistException, IncorrectPasswordException {
        if (name.isEmpty() || password.isEmpty()) {
            throw new EmptyTextException();
        }
        if (name.length()<5 || password.length()<5 || name.length()>20 || password.length()>20) {
            throw new IllegalTextSymbolCountException();
        }
        if ( userDao.getUserByName(name) != null) {
            throw new ObjectAlreadyExistException();
        }
        if (!password.equals(passwordRepeat)) {
            throw new IncorrectPasswordException();
        }
        put(name, password, email, role);
    }

    private synchronized void put(String name, String password, String email, User.Role role) {
        User user = new User(name, password, email, role);
        userDao.addUser(user);
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
            userDao.deleteUser(userId);
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
        return userDao.getUserCount();
    }
}
