package lv.ak07178.testapp.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void sendMail(String emailTo, String subject, String text) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("oleglavrenev@gmail.com");
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setText(text);
            javaMailSender.send(mimeMessage);
            log.info("Sucessfully sent mail to " + subject);
        } catch (MessagingException e) {
            log.error("Exception while sending mail",e);
        }
    }
}
