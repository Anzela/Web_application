package lv.ak07178.testapp.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:kirill.afanasjev@odnoklassniki.ru">Kirill Afanasjev</a>
 */
@Component
public class Scheduler {

    @Scheduled( fixedDelay = 1000 )
    public void expireArchive() {
        System.out.println("running");;
    }



}
