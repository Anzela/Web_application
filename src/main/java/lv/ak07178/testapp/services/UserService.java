package lv.ak07178.testapp.services;

import org.springframework.stereotype.Service;

import lv.ak07178.testapp.domain.User;

@Service
public class UserService {
    public User getUserById(Long userId) {
        if (userId == 1) {
            User user = new User();
            user.setName("Kirill Afanasjev");
            return user;
        }
        return null;
    }
}
