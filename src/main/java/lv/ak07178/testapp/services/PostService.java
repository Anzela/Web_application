package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Post;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class PostService {
    private HashMap<Long, Post> posts = new HashMap<Long, Post>();
    private long postId;

    @PostConstruct
    public void init() {
        put(new Post(Post.PostFilter.NEWS, 2L, "Сайт находится в разработке", "В настоящий момент сайт находится в разработке. У вас есть возможность активно влиять на процесс разработки данного продукта. Мы будем ждать Ваших пожеланий и предложений по его усовершенствованию."));
        put(new Post(Post.PostFilter.DISCUSSIONS, 1L, "Давайте обсудим...", "Почему нет коммитов? ^_^"));
        put(new Post(Post.PostFilter.JOKES, 3L, "Из жизни компьютерщиков", "Жизнь слишком коротка, чтобы каждый раз прикручивать крышку от системника обратно. Просто прислони её..."));
        put(new Post(Post.PostFilter.NEWS, 2L, "Постинг", "В ближайшее время планируем сделать создание новых постов и новостей на нашем сайте"));
    }

    private void put(Post post) {
        postId++;
        post.setPostId(postId);
        posts.put(post.getPostId(), post);
    }

    public Post getPostById(long postId){
        return posts.get(postId);
    }

    public List<Post> getAllPosts(){
        return new ArrayList<Post>(posts.values());
    }

    public List<Post.PostFilter> getAllPostFilters(){
        List<Post.PostFilter> postFilterList = Arrays.asList(Post.PostFilter.values());
        return postFilterList;
    }

        public List<Post> getPostsByFilter(Post.PostFilter postFilter) {
        List<Post> result = new ArrayList<Post>();
        for (Post post : posts.values()) {
            if (post.getPostFilter() == postFilter) {
                result.add(post);
            }
        }
        return result;
    }

    public void addPost(Post.PostFilter postFilter, long userId, long postId, String postTitle, String postText) {
        if (postText == null) {
            throw new IllegalArgumentException("Empty text");
        }
        if (postTitle == null) {
            throw new IllegalArgumentException("Empty title");
        }
        Post post = getPostById(postId);
        if (post.getUserId() == userId) {
            post.setPostFilter(postFilter);
            post.setPostTitle(postTitle);
            post.setPostText(postText);
        } else {
            throw new IllegalArgumentException("Wrong user id");
        }
    }
}
