package lv.ak07178.testapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.PostService;

/**
 * @author <a href="mailto:kirill.afanasjev@odnoklassniki.ru">Kirill Afanasjev</a>
 */
@Controller
public class UploadPhotoController {

    @Autowired
    private PostService postService;

    private static final Logger log = LoggerFactory.getLogger(UploadPhotoController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/upload")
    public String getAdminSections() {
        return "upload";
    }

    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(
            @RequestParam("postId") long postId,
            @RequestParam("file") MultipartFile file
            ) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                postService.savePhoto(postId, bytes);

                log.info("FAILIK SKACHALSJA");

                return "You successfully uploaded file=";
            } catch (Exception e) {
                return "You failed to upload " +" => " + e.getMessage();
            }
        } else {
            return "You failed to upload "
                    + " because the file was empty.";
        }
    }
}
