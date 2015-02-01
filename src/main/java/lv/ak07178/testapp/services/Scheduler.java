package lv.ak07178.testapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private UserService userService;

    @Scheduled( fixedDelay = 100000 )
    public void expireArchive() {
        log.info("Current user count is " + userService.getUserCount());
    }

}
