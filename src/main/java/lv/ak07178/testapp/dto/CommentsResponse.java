package lv.ak07178.testapp.dto;

import java.util.ArrayList;
import java.util.List;

public class CommentsResponse {

    List<CommentDTO> comments = new ArrayList<CommentDTO>();


    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
