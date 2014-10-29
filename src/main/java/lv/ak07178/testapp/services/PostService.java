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
        put(new Post(2L, Post.Section.NEWS, 1L, "Сайт находится в разработке", "В настоящий момент сайт находится в разработке. У вас есть возможность активно влиять на процесс разработки данного продукта. Мы будем ждать Ваших пожеланий и предложений по его усовершенствованию."));
        put(new Post(1L, Post.Section.PUBLIC_DISCUSSIONS, 2L, "Давайте обсудим...", "Почему нет коммитов? ^_^"));
        put(new Post(3L, Post.Section.JOKES, 3L, "Из жизни компьютерщиков", "Жизнь слишком коротка, чтобы каждый раз прикручивать крышку от системника обратно. Просто прислони её..."));
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
