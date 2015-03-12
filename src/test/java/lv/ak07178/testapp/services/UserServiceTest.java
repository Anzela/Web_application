package lv.ak07178.testapp.services;

import lv.ak07178.testapp.services.exceptions.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import lv.ak07178.testapp.domain.User;

@Ignore
public class UserServiceTest {

    private static final String USERNAME = "anzelka";
    private static final String PASSWORD = "anzelka";

    private static final String USERNAME2 = "username2";
    private static final String PASSWORD2 = "password2";

    private static final String EMAIL = "anzela_k@mail.ru";

    private static final String WRONG_USER = "WRONGUSER";
    private static final String WRONG_PASSWORD = "WRONGPASS";

    private static final String SHORT_PASSWORD = "pass";
    private static final String SHORT_USERNAME = "name";

    private UserService userService;

    @Before
    public void init() throws EmptyTextException, IllegalTextSymbolCountException, ObjectAlreadyExistException, IncorrectPasswordException {
        userService = new UserService();
        userService.init();

        userService.addUser(USERNAME,PASSWORD, PASSWORD, EMAIL, User.Role.ADMINISTRATOR);
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

    @Test(expected = ObjectAlreadyExistException.class)
    public void testObjectAlreadyExist() throws IllegalTextSymbolCountException, ObjectAlreadyExistException, EmptyTextException, IncorrectPasswordException {
        userService.addUser(USERNAME, PASSWORD2, PASSWORD2, EMAIL, User.Role.USER);
    }

    @Test(expected = IllegalTextSymbolCountException.class)
    public void testShortPass() throws IllegalTextSymbolCountException, ObjectAlreadyExistException, EmptyTextException, IncorrectPasswordException {
        userService.addUser(USERNAME2, SHORT_PASSWORD, SHORT_PASSWORD, EMAIL, User.Role.USER);
    }

    @Test(expected = IllegalTextSymbolCountException.class)
    public void testShortLogin() throws IllegalTextSymbolCountException, ObjectAlreadyExistException, EmptyTextException, IncorrectPasswordException {
        userService.addUser(SHORT_USERNAME, PASSWORD2, PASSWORD2, EMAIL, User.Role.USER);
    }

    @Test(expected = EmptyTextException.class)
    public void testEmptyLogin() throws IllegalTextSymbolCountException, ObjectAlreadyExistException, EmptyTextException, IncorrectPasswordException {
        userService.addUser("" , PASSWORD2, PASSWORD2, EMAIL, User.Role.USER);
    }

    @Test(expected = EmptyTextException.class)
    public void testEmptyPassword() throws IllegalTextSymbolCountException, ObjectAlreadyExistException, EmptyTextException, IncorrectPasswordException {
        userService.addUser(USERNAME2, "", "", EMAIL, User.Role.USER);
    }

    @Test
    public void testCorrectRegistration() throws IllegalTextSymbolCountException, ObjectAlreadyExistException, EmptyTextException, IncorrectPasswordException {
        userService.addUser(USERNAME2, PASSWORD2, PASSWORD2, EMAIL, User.Role.USER);
    }
}