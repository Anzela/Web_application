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

    public Post getPostById(long postId){
        return posts.get(postId);
    }

    public List<Post> getAllPosts(){
        List<Post> result = new ArrayList<Post>(posts.values());
        Collections.sort(result, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return result;

    }

    public Post.Section[] getAllSections() {
        return Post.Section.values();
    }

    public List<Post> getPostsByUserId(long userId) {
        List<Post> result = new ArrayList<Post>();
        for (Post post : getAllPosts()) {
            if (post.getAuthorId() == userId) {
                result.add(post);
            }
        }
        return result;
    }

        public List<Post> getPostsBySection(Post.Section section) {
        List<Post> result = new ArrayList<Post>();
        for (Post post : getAllPosts()) {
            if (post.getSection() == section) {
                result.add(post);
            }
        }



        return result;
    }

    public List<Post> getPostsByType(Post.Section.Type type) {
        List<Post> result = new ArrayList<Post>();
        for (Post post : getAllPosts()) {
            if (post.getSection().getType() == type) {
                result.add(post);

            }
        }
        return result;
    }

    public List<Post.Section> getSectionsByType(Post.Section.Type type) {
        List<Post.Section> result = new ArrayList<Post.Section>();
        for (Post.Section section : Post.Section.values()) {
            if (section.getType() == type) {
                result.add(section);
            }
        }
        return result;
    }
}
