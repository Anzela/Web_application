package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.domain.User;
import lv.ak07178.testapp.services.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PostServiceTest {

    private static final String USERNAME = "anzelka";
    private static final String PASSWORD = "anzelka";

    private static final String POST_TEXT = "text";
    private static final String POST_TITLE = "title";

    private PostService postService;
    private UserService userService;


    @Before
    public void init() throws IOException, ClassNotFoundException, IllegalTextSymbolCountException, ObjectAlreadyExistException, EmptyTextException, UserNotFoundException, IncorrectPasswordException {
        postService = new PostService();
        userService = new UserService();
        userService.addUser(USERNAME, PASSWORD, User.Role.ADMINISTRATOR);
    }

    @Test
    public void testAddPost() throws EmptyTextException, IllegalTitleSymbolCountException, EmptyTitleException, IllegalTextSymbolCountException, UserNotFoundException, IncorrectPasswordException {
//        userService.loginUser(USERNAME, PASSWORD);
//        postService.addPost(Post.Section.NEWS, POST_TITLE, POST_TEXT);
    }
}
