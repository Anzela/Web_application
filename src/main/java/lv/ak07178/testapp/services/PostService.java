package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Comment;
import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.exceptions.*;
import lv.ak07178.testapp.session.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PostService {
    public static final String DATA_DB = "data.db";
    private HashMap<Long, Post> posts = new HashMap<Long, Post>();
    private long postId;
    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private CommentService commentService;
    @Autowired
    private CurrentUser currentUser;

    @PostConstruct
    public void init() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(new File(DATA_DB));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            posts = (HashMap<Long, Post>) ois.readObject();
            postId = (Long)ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void save() throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(new File(DATA_DB));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(posts);
            oos.writeObject((Long) postId);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void put(Post post) {
        postId++;
        post.setId(postId);
        post.setDateTime(new Date().getTime());
        posts.put(post.getId(), post);
    }

    public void addPost(Post.Section section, String postTitle, String postText)
            throws EmptyTextException, EmptyTitleException, IllegalTextSymbolCountException, IllegalTitleSymbolCountException {
        if (postText.isEmpty()) {
            throw new EmptyTextException();
        }
        if (postTitle.isEmpty()) {
            throw new EmptyTitleException();
        }
        if (postText.length()>10000){
            throw new IllegalTextSymbolCountException();
        }
        if (postTitle.length()>150){
            throw new IllegalTitleSymbolCountException();
        }
        Post post = new Post(section, currentUser.getId(), postTitle, postText);
        put(post);
    }

    public Post getPostById(long postId){
        return posts.get(postId);
    }

    public String getPostCreationTime(Post post){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy");
        String postDate = dateFormat.format(new Date(post.getDateTime()));
        String currentDate = dateFormat.format(new Date().getTime());
        //вычисляем вчерашнюю дату
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterdayDate = dateFormat.format(cal.getTime());

        if (postDate.equals(currentDate)){
            dateFormat = new SimpleDateFormat("Сегодня, H:mm:ss");
            postDate = dateFormat.format(new Date(post.getDateTime()));
        }
        else if (postDate.equals(yesterdayDate)){
            dateFormat = new SimpleDateFormat("Вчера, H:mm:ss");
            postDate = dateFormat.format(new Date(post.getDateTime()));
        }
        else {
            dateFormat = new SimpleDateFormat("dd.M.yyyy, H:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Riga"));
            postDate = dateFormat.format(new Date(post.getDateTime()));
        }
        return postDate;
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

    public void deletePost(long postId){
        if (posts.remove(postId)==null) {
        }
        commentService.deletePostComments(postId);
        if (posts.get(postId) == null) {
            log.info("Delete post with id " + postId);
        }
    }

    public boolean isCurrentUserPostAuthor(long postId) {
        Long currentUserId = currentUser.getId();
        if (currentUserId == null) {
            return false;
        }
        return getPostById(postId).getAuthorId() == currentUserId;
    }

    public void deleteUserPosts(long userId){
        for (Post post : getPostsByUserId(userId)) {
            long postId = post.getId();
            deletePost(postId);
        }
    }
}
