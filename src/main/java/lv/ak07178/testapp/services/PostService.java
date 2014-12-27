package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.services.exceptions.IncorrectRemoveException;
import lv.ak07178.testapp.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.*;

@Service
public class PostService {
    public static final String DATA_DB = "data.db";
    private HashMap<Long, Post> posts = new HashMap<Long, Post>();
    private long postId;

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

    public void deletePost(long postId) throws IncorrectRemoveException {
        if (posts.remove(postId)==null) {
            throw new IncorrectRemoveException();
        }
        commentService.deletePostComments(postId);
    }

    public boolean isCurrentUserPostAuthor(long postId) {
        Long currentUserId = currentUser.getId();
        if (currentUserId == null) {
            return false;
        }
        return getPostById(postId).getAuthorId() == currentUserId;
    }
}
