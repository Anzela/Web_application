package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Blog;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
        public Blog getBlogById(Long blogId) {
            if (blogId == 1) {
                Blog blog = new Blog();
                blog.setDescription("Text");
                return blog;
            }
            return null;
        }
}
