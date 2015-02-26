package lv.ak07178.testapp.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.PostService;

/**
 * @author <a href="mailto:kirill.afanasjev@odnoklassniki.ru">Kirill Afanasjev</a>
 */
@Controller
public class PostImageController {

    private static final Logger log = LoggerFactory.getLogger(UploadPhotoController.class);

    @Autowired
    private PostService postService;

    @RequestMapping("/photo/{postId}")
    public ResponseEntity<byte[]> testphoto
            (
            @PathVariable long postId
            )
            throws IOException {

        log.info("HANDLING METHOD CALL");

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        Post post = postService.getPostById(postId);
        if (post == null || post.getPhotoBytes() == null) {
            return new ResponseEntity<byte[]>(
                    new byte[]{},
                    headers,
                    HttpStatus.CREATED
            );
        }
        return new ResponseEntity<byte[]>(
                post.getPhotoBytes(),
                headers,
                HttpStatus.CREATED
        );
    }
}
