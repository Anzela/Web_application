package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Post;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class PostService {
    private HashMap<Long, Post> posts = new HashMap<Long, Post>();
    private long postId;

    @PostConstruct
    public void init() {
        put(new Post(Post.Section.NEWS, 1L, "Сайт находится в разработке", "В настоящий момент сайт находится в разработке. У вас есть возможность активно влиять на процесс разработки данного продукта. Мы будем ждать Ваших пожеланий и предложений по его усовершенствованию."));
        put(new Post(Post.Section.DISCUSSIONS, 2L, "Давайте обсудим...", "Почему нет коммитов? ^_^"));
        put(new Post(Post.Section.JOKES, 3L, "Из жизни компьютерщиков", "Жизнь слишком коротка, чтобы каждый раз прикручивать крышку от системника обратно. Просто прислони её..."));
        put(new Post(Post.Section.NEWS, 1L, "Постинг", "В ближайшее время планируем сделать создание новых постов и новостей на нашем сайте"));
        put(new Post(Post.Section.NEWS, 1L, "Новые посты", "СВЕРШИЛОСЬ ЧУДО! Постинг заработал =D"));
    }

    private void put(Post post) {
        postId++;
        post.setId(postId);
        posts.put(post.getId(), post);
    }

    public Post getPostById(long postId){
        return posts.get(postId);
    }

    public List<Post> getPostsByUserId(long userId) {
        List<Post> userPosts = new ArrayList<Post>();
        for (Map.Entry entry : posts.entrySet()) {
            Post value = (Post) entry.getValue();
            if (value.getAuthorId() == userId) {
                userPosts.add(value);
            }
        }
        return userPosts;
    }

    public List<Post> getAllPosts(){
        return new ArrayList<Post>(posts.values());
    }

    public List<Post.Section> getAllPostFilters(){
        List<Post.Section> sectionList = Arrays.asList(Post.Section.values());
        return sectionList;
    }

        public List<Post> getPostsByFilter(Post.Section section) {
        List<Post> result = new ArrayList<Post>();
        for (Post post : posts.values()) {
            if (post.getSection() == section) {
                result.add(post);
            }
        }
        return result;
    }

    public void addPost(Post.Section section, long userId, String postTitle, String postText) {
        if (postText.isEmpty()) {
            throw new IllegalArgumentException("Empty text");
        }
        if (postTitle.isEmpty()) {
            throw new IllegalArgumentException("Empty title");
        }
        Post post = new Post(section, userId, postTitle, postText);
        put(post);
    }
}
