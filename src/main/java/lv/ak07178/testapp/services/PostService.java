package lv.ak07178.testapp.services;

import lv.ak07178.testapp.domain.Post;
import lv.ak07178.testapp.dto.PostDTO;
import lv.ak07178.testapp.dto.SectionDTO;
import lv.ak07178.testapp.services.exceptions.*;
import lv.ak07178.testapp.session.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private UserService userService;

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
    public void save(){
        try {
            log.info("Saving posts..");
            FileOutputStream fos = new FileOutputStream(new File(DATA_DB));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(posts);
            oos.writeObject((Long) postId);
            oos.close();
            log.info("Loaded posts.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void put(Post post) {
        postId++;
        post.setId(postId);
        post.setCreationDate(new Date().getTime());
        posts.put(post.getId(), post);
    }

    public void addPost(Post.Section section, String postTitle, String postText, MultipartFile file)
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
        save();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                savePhoto(postId, bytes);
            } catch (Exception e) {
        }
        }
    }

    public Post getPostById(long postId){
        return posts.get(postId);
    }

    public String getPostCreationDate(Post post){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy");
        String postDate = dateFormat.format(new Date(post.getCreationDate()));
        String currentDate = dateFormat.format(new Date().getTime());
        //вычисляем вчерашнюю дату
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterdayDate = dateFormat.format(cal.getTime());

        if (postDate.equals(currentDate)){
            dateFormat = new SimpleDateFormat("H:mm:ss");
            postDate = "Сегодня,  " + dateFormat.format(new Date(post.getCreationDate()));
        }
        else if (postDate.equals(yesterdayDate)){
            dateFormat = new SimpleDateFormat("H:mm:ss");
            postDate = "Вчера, " + dateFormat.format(new Date(post.getCreationDate()));
        }
        else {
            dateFormat = new SimpleDateFormat("dd.M.yyyy, H:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Riga"));
            postDate = dateFormat.format(new Date(post.getCreationDate()));
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

    public List<SectionDTO> getSectionsByType(Post.Section.Type type) {
        List<SectionDTO> result = new ArrayList<SectionDTO>();
        for (Post.Section section : Post.Section.values()) {
            if (section.getType() == type) {
                result.add(convertToDto(section));
            }
        }
        return result;
    }

    private SectionDTO convertToDto(Post.Section section) {
        SectionDTO result = new SectionDTO();
        result.setDescription(section.getDescription());
        result.setTitle(section.getTitle());
        result.setPostCount(getPostsBySection(section).size());
        result.setSection(section);
        return result;
    }

    public void deletePost(long postId){
        if (isCurrentUserPostAuthor(postId)|| userService.isCurrentUserAdmin()) {
            posts.remove(postId);
            commentService.deletePostComments(postId);
            save();
        }
        else {
            log.error("Произошла ошибка при удалении поста");
        }
    }

    public boolean isCurrentUserPostAuthor(long postId) {
        Long currentUserId = currentUser.getId();
        return currentUserId != null && getPostById(postId).getAuthorId() == currentUserId;
    }

    public void deleteUserPosts(long userId){
        for (Post post : getPostsByUserId(userId)) {
            long postId = post.getId();
            deletePost(postId);
        }
    }

    public List<PostDTO> convertToDTOs(List<Post> posts) {
        List<PostDTO> result = new ArrayList<PostDTO>();
        for (Post post : posts) {
            result.add(convertToDto(post));
        }
        return result;

    }

    private PostDTO convertToDto(Post post) {
        PostDTO result = new PostDTO();
        long postId = post.getId();
        result.setAuthorId(post.getAuthorId());
        result.setId(post.getId());
        result.setText(post.getText());
        result.setTitle(post.getTitle());
        result.setCreationDate(post.getCreationDate());
        result.setFormattedCreationDate(getPostCreationDate(post));
        result.setSection(post.getSection());
        result.setCommentCount(commentService.getCommentsByPostId(postId).size());
        result.setViewCount(post.getViewCounter());
        result.setPhotoBytes(post.getPhotoBytes());
        return result;
    }

    public void editPost(Post post, String newPostTitle, String newPostText) throws
            IllegalTitleSymbolCountException, IllegalTextSymbolCountException, EmptyTitleException, EmptyTextException {
        if (newPostText.isEmpty()) {
            throw new EmptyTextException();
        }
        if (newPostTitle.isEmpty()) {
            throw new EmptyTitleException();
        }
        if (newPostText.length()>10000){
            throw new IllegalTextSymbolCountException();
        }
        if (newPostTitle.length()>150){
            throw new IllegalTitleSymbolCountException();
        }
        post.setText(newPostText);
        post.setTitle(newPostTitle);
        posts.put(post.getId(), post);
        save();
    }

    public void incrementPostViewCounter(long postId) {
        Post post = getPostById(postId);
        post.setViewCounter(post.getViewCounter() + 1);
    }

    public void savePhoto(long postId, byte[] photoBytes) {
        getPostById(postId).setPhotoBytes(photoBytes);
        save();
    }
}
