package lv.ak07178.testapp.services;

import org.junit.Before;
import org.junit.Test;

import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.exceptions.IncorrectPasswordException;
import lv.ak07178.testapp.services.exceptions.UserNotFoundException;

public class UserServiceTest {

    private static final String USERNAME = "anzelka";
    private static final String PASSWORD = "anzelka";

    private static final String WRONG_USER = "WRONGUSER";
    private static final String WRONG_PASSWORD = "WRONGPASS";

    private UserService userService;

    @Before
    public void init() {
        userService = new UserService();
        userService.init();

        userService.addUser(USERNAME,PASSWORD, User.Role.ADMINISTRATOR);
    }

    @Test
    public void testCorrectAuth() throws UserNotFoundException, IncorrectPasswordException {
        userService.authenticateUser(USERNAME, PASSWORD);
    }

    @Test(expected = UserNotFoundException.class)
    public void testWrongUser() throws UserNotFoundException, IncorrectPasswordException {
        userService.authenticateUser(WRONG_USER, PASSWORD);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void testWrongPass() throws UserNotFoundException, IncorrectPasswordException {
        userService.authenticateUser(USERNAME, WRONG_PASSWORD);
    }

}