package lv.ak07178.testapp.services;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordGeneratorService {
    public String generatePassword() {
        return RandomStringUtils.randomAscii(10);
    }
}
