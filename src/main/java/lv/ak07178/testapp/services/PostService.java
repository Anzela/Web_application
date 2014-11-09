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

    @PostConstruct
    public void init() {
        put(new Post(2L, Post.Section.NEWS, 1L, "Сайт находится в разработке", "В настоящий момент сайт находится в разработке. У вас есть возможность активно влиять на процесс разработки данного продукта. Мы будем ждать Ваших пожеланий и предложений по его усовершенствованию."));
        put(new Post(1L, Post.Section.DISCUSSIONS, 2L, "Давайте обсудим...", "Почему нет коммитов? ^_^"));
        put(new Post(3L, Post.Section.JOKES, 3L, "Из жизни компьютерщиков", "Жизнь слишком коротка, чтобы каждый раз прикручивать крышку от системника обратно. Просто прислони её..."));
        put(new Post(2L, Post.Section.NEWS, 4L, "Постинг", "В ближайшее время планируем сделать создание новых постов и новостей на нашем сайте"));
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

    public List<Post.Section> getAllPostSections(){
        List<Post.Section> sectionList = Arrays.asList(Post.Section.values());
        return sectionList;
    }

        public List<Post> getPostsBySection(Post.Section section) {
        List<Post> result = new ArrayList<Post>();
        for (Post post : posts.values()) {
            if (post.getSection() == section) {
                result.add(post);
            }
        }
        return result;
    }

    public void addPost(long userId, Post.Section section, long postId, String postTitle, String postText) {
        if (postText == null) {
            throw new IllegalArgumentException("Empty text");
        }
        if (postTitle == null) {
            throw new IllegalArgumentException("Empty title");
        }
        Post newPost = new Post(userId, section, postId, postTitle, postText);
        newPost.setUserId(userId);
        newPost.setSection(section);
        newPost.setPostId(postId);
        newPost.getPostTitle();
        newPost.setPostText(postText);
        posts.put(postId, newPost);
        postId++;
    }
}
