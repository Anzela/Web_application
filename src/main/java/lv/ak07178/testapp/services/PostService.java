package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PostService {
    private HashMap<Long, Post> posts = new HashMap<Long, Post>();

    @PostConstruct
    public void init() {
        put(new Post(2L, Post.Section.NEWS, 1L, "На данный момент сайт в разработке."));
        put(new Post(1L, Post.Section.PUBLIC_DISCUSSIONS, 2L, "Давайте обсудим..."));
        put(new Post(3L, Post.Section.JOKES, 3L, ""));
    }

    private void put(Post post) {
        if (posts.containsKey(post.getPostId())) {
            throw new IllegalArgumentException("Post with id " + post.getPostId() + " already exist");
        }
        posts.put(post.getPostId(), post);
    }

    public Post getPostById(long postId){
        return posts.get(postId);
    }

    public List<Post> getAllPosts(){
        return new ArrayList<Post>(posts.values());
    }
}
